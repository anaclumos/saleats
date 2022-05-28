package controller;

import java.io.*;
import java.sql.*;
import java.util.ArrayList;

import model.User;
import model.Business;
import model.BusinessId;
import model.Category;
import model.Constant;

public class JdbcDriver {
	String db = "jdbc:mysql://localhost:3306/saleats";
	// get it from constant
	String user = Constant.dbUsername;
	String pwd = Constant.dbPassword;

	// use singleton pattern
	private static JdbcDriver shared = new JdbcDriver();

	public static JdbcDriver getInstance() {
		return shared;
	}

	public JdbcDriver() {
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
	}

	public boolean dataExists() {
		try {
			Connection conn = DriverManager.getConnection(db, user, pwd);
			Statement stmt = conn.createStatement();
			ResultSet rs = stmt.executeQuery("SELECT * FROM Restaurant");
			if (rs.next()) {
				return true;
			}
			conn.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return false;
	}

	public boolean userExists(String email) {
		String sql = "SELECT * FROM User WHERE user_email = '" + email + "';";
		try (Connection conn = DriverManager.getConnection(db, user, pwd);
				CallableStatement stmt = conn.prepareCall(sql);) {
			ResultSet rs = stmt.executeQuery();
			if (rs.next())
				return true;
			else
				return false;
		} catch (SQLException sqle) {
			System.out.println("SQLException: " + sqle.getMessage());
			return false;
		}
	}

	public void insertUser(String email, String name, String password) {
		String sql = "INSERT INTO User (user_email,user_name, user_password) VALUES ('" + email + "', '" + name + "', '"
				+ password + "');";
		try (Connection conn = DriverManager.getConnection(db, user, pwd);
				CallableStatement stmt = conn.prepareCall(sql);) {
			stmt.executeUpdate();
		} catch (SQLException sqle) {
			System.out.println("SQLException: " + sqle.getMessage());
		}
	}

	public boolean checkPassword(String email, String password) {
		String sql = "SELECT * FROM User WHERE user_email = '" + email + "' AND user_password = '" + password + "';";
		try (Connection conn = DriverManager.getConnection(db, user, pwd);
				CallableStatement stmt = conn.prepareCall(sql);) {
			ResultSet rs = stmt.executeQuery();
			if (rs.next()) {
				return true;
			} else {
				return false;
			}
		} catch (SQLException sqle) {
			System.out.println("SQLException: " + sqle.getMessage());
			return false;
		}
	}

	public User getUser(String email) {
		String sql = "SELECT * FROM User WHERE user_email = '" + email + "';";
		try (Connection conn = DriverManager.getConnection(db, user, pwd);
				CallableStatement stmt = conn.prepareCall(sql);) {
			ResultSet rs = stmt.executeQuery();
			if (rs.next()) {
				return new User(rs.getString("user_name"), rs.getString("user_email"));
			} else {
				return null;
			}
		} catch (SQLException sqle) {
			System.out.println("SQLException: " + sqle.getMessage());
			return null;
		}
	}

	public void executeSqlFile(String sqlFile) {
		String sql = "";
		try (BufferedReader br = new BufferedReader(new FileReader(sqlFile))) {
			String line;
			while ((line = br.readLine()) != null) {
				sql += line + "\n";
			}
			try (Connection conn = DriverManager.getConnection(db, user, pwd);
					CallableStatement stmt = conn.prepareCall(sql);) {
				stmt.executeUpdate();
			} catch (SQLException sqle) {
				System.out.println("SQLException: " + sqle.getMessage());
			}
		} catch (IOException ioe) {
			System.out.println("IOException: " + ioe.getMessage());
		}
	}

	public int insert(String sql, String... params) {
		try (Connection conn = DriverManager.getConnection(db, user, pwd)) {
			PreparedStatement stmt = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
			for (int i = 0; i < params.length; i++) {
				stmt.setString(i + 1, params[i]);
			}
			stmt.executeUpdate();
			ResultSet rs = stmt.getGeneratedKeys();
			if (rs.next() && rs != null) {
				return rs.getInt(1);
			}
		} catch (SQLException sqle) {
			System.out.println("SQLException: " + sqle.getMessage());
		}
		return -1;
	}

	public void insertBusinesses(Business[] businesses) throws UnsupportedEncodingException {
		for (Business restaurant : businesses) {
			String restaurantName = restaurant.getName();
			String reviewCount = restaurant.getReviewCount() + "";
			String rating = restaurant.getRating() + "";
			String ratingId = insert("INSERT INTO Rating_details (review_count, rating) VALUES (? , ?)", reviewCount,
					rating) + "";
			String imageUrl = restaurant.getImage();
			String address = String.join(", ", restaurant.getLocation().getDisplayAddress());
			String phone = restaurant.getPhone();
			String estimatedPrice = restaurant.getPrice();
			String restaurantUid = restaurant.getId();
			String yelpUrl = restaurant.getUrl();
			String detailsId = insert(
					"INSERT INTO Restaurant_details (image_url, address, phone_no, estimated_price, yelp_url) VALUES (?, ?, ?, ?, ?)",
					imageUrl, address, phone, estimatedPrice, yelpUrl) + "";
			int restaurantId = insert(
					"INSERT INTO Restaurant (restaurant_name, restaurant_uid, details_id, rating_id) VALUES (?, ?, ?, ?)",
					restaurantName, restaurantUid, detailsId, ratingId);
			Category[] categories = restaurant.getCategories();

			for (Category category : categories) {
				String categoryName = category.getTitle();
				insert("INSERT INTO Category (category_name, restaurant_id) VALUES ('" + categoryName + "', "
						+ restaurantId + ");");
			}
		}
	}

	public BusinessId[] searchBusinessByName(String query, String sort) {
		String sql = "";
		switch (sort) {
			case "price":
				sql = "SELECT * FROM Restaurant r JOIN Restaurant_details d ON r.details_id = d.details_id WHERE restaurant_name LIKE (?) ORDER BY estimated_price ASC, restaurant_name ASC;";
				break;
			case "review_count":
				sql = "SELECT * FROM Restaurant r JOIN Rating_details d ON r.rating_id = d.rating_id WHERE restaurant_name LIKE (?) ORDER BY review_count DESC, restaurant_name ASC;";
				break;
			case "rating":
				sql = "SELECT * FROM Restaurant r JOIN Rating_details d ON r.rating_id = d.rating_id WHERE restaurant_name LIKE (?) ORDER BY rating DESC, restaurant_name ASC;";
				break;
		}
		ArrayList<BusinessId> businessesId = new ArrayList<>();
		try (Connection conn = DriverManager.getConnection(db, user, pwd);
				PreparedStatement stmt = conn.prepareStatement(sql);) {
			stmt.setString(1, "%" + query + "%");
			ResultSet rs = stmt.executeQuery();
			while (rs.next()) {
				businessesId.add(new BusinessId(rs.getString("restaurant_id"), rs.getString("restaurant_uid"),
						rs.getString("restaurant_name")));
			}
			return businessesId.toArray(new BusinessId[businessesId.size()]);
		} catch (SQLException sqle) {
			System.out.println("SQLException: " + sqle.getMessage());
		}
		return null;
	}

	public BusinessId[] searchBusinessByCategory(String query, String sort) {
		String sql = "";
		switch (sort) {
			case "price":
				sql = "SELECT * FROM Restaurant r JOIN Restaurant_details d ON r.details_id = d.details_id JOIN Category c ON r.restaurant_id = c.restaurant_id WHERE category_name LIKE (?) ORDER BY estimated_price ASC, restaurant_name ASC;";
				break;
			case "review_count":
				sql = "SELECT * FROM Restaurant r JOIN Restaurant_details d ON r.details_id = d.details_id JOIN Category c ON r.restaurant_id = c.restaurant_id JOIN Rating_details k ON r.rating_id = k.rating_id WHERE category_name LIKE (?) ORDER BY review_count DESC, restaurant_name ASC;";
				break;
			case "rating":
				sql = "SELECT * FROM Restaurant r JOIN Restaurant_details d ON r.details_id = d.details_id JOIN Category c ON r.restaurant_id = c.restaurant_id JOIN Rating_details k ON r.rating_id = k.rating_id WHERE category_name LIKE (?) ORDER BY rating DESC, restaurant_name ASC;";
				break;
		}
		ArrayList<BusinessId> businessesId = new ArrayList<>();
		try (Connection conn = DriverManager.getConnection(db, user, pwd);
				PreparedStatement stmt = conn.prepareStatement(sql);) {
			stmt.setString(1, "%" + query + "%");
			ResultSet rs = stmt.executeQuery();
			while (rs.next()) {
				businessesId.add(new BusinessId(rs.getString("restaurant_id"), rs.getString("restaurant_uid"),
						rs.getString("restaurant_name")));
			}
			return businessesId.toArray(new BusinessId[businessesId.size()]);
		} catch (SQLException sqle) {
			System.out.println("SQLException: " + sqle.getMessage());
		}
		return null;
	}

	public Business searchBusiness(BusinessId businessId) {
		String sql = "SELECT * FROM Restaurant r JOIN Restaurant_details d ON r.details_id = d.details_id JOIN Category c ON r.restaurant_id = c.restaurant_id JOIN Rating_details k ON r.rating_id = k.rating_id WHERE restaurant_uid = (?)";
		try (Connection conn = DriverManager.getConnection(db, user, pwd);
				PreparedStatement stmt = conn.prepareStatement(sql);) {
			stmt.setString(1, businessId.getBusinessUid());
			Business business = new Business();
			ResultSet rs = stmt.executeQuery();
			ArrayList<Category> categories = new ArrayList<>();
			while (rs.next()) {
				business.setId(rs.getString("restaurant_id"));
				business.setName(rs.getString("restaurant_name"));
				business.setId(rs.getString("restaurant_uid"));
				business.setImage(rs.getString("image_url"));
				business.setDisplayAddress(rs.getString("address"));
				business.setPhone(rs.getString("phone_no"));
				business.setPrice(rs.getString("estimated_price"));
				business.setUrl(rs.getString("yelp_url"));
				business.setRating(Double.parseDouble(rs.getString("rating")));
				business.setReviewCount(Integer.parseInt(rs.getString("review_count")));
				categories.add(new Category(rs.getString("category_name")));
			}
			business.setCategories(categories.toArray(new Category[categories.size()]));
			return business;
		} catch (SQLException sqle) {
			System.out.println("SQLException: " + sqle.getMessage());
		}
		return null;
	}
}