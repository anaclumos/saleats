package controller;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.HashMap;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import model.Business;
import model.BusinessId;
import model.Constant;
import model.Status;
import model.User;

public class Service {
	// make singleton instance
	private static Service shared = new Service();
	private JdbcDriver jdbc = JdbcDriver.getInstance();

	// private constructor
	private Service() {
	}

	public Status registerUser(String email, String name, String password, String confirmPassword) {
		// check if user already exists
		if (jdbc.userExists(email)) {
			return new Status(false, "User already exists. Please try another email.");
		}
		// insert new user into database
		if (!isValidEmail(email)) {
			return new Status(false, "Invalid email.");
		}

		if (!isValidName(name)) {
			return new Status(false, "Invalid name. You can only use alphabets and space.");
		}
		if (password.isEmpty()) {
			return new Status(false, "Password cannot be empty.");
		}
		if (!password.equals(confirmPassword)) {
			return new Status(false, "Password does not match. Please reconfirm the password.");
		}
		jdbc.insertUser(email, name, password);
		return new Status(true, "User registered successfully");

	}

	public User login(String email, String password) {
		if (!jdbc.userExists(email)) {
			return new User(false);
		}
		if (jdbc.checkPassword(email, password)) {
			return jdbc.getUser(email);
		} else {
			return null;
		}
	}

	public Cookie createCookie(String name) throws UnsupportedEncodingException {
		if (name == null || name.isEmpty()) {
			return null;
		}
		name = URLEncoder.encode(name, "utf-8").replaceAll("\\+", "%20");
		Cookie cookie = new Cookie("name", name);
		cookie.setMaxAge(60 * 60 * 24 * 7); // Week
		return cookie;
	}

	public void removeCookie(HttpServletRequest request, HttpServletResponse response) {
		Cookie[] cookies = request.getCookies();
		if (cookies != null) {
			for (Cookie cookie : cookies) {
				if (cookie.getName().equals("name")) {
					cookie.setMaxAge(0);
					cookie.setValue(null);
					response.addCookie(cookie);
				}
			}
		}
	}

	// helper methods
	public static Service getInstance() {
		return shared;
	}

	public static boolean isValidName(String name) {
		return Constant.namePattern.matcher(name).matches();
	}

	public static boolean isValidEmail(String email) {
		if (email == null) {
			return false;
		}
		return Constant.emailPattern.matcher(email).matches();
	}

	public BusinessId[] search(String type, String query, String sort) {
		if (type == null) {
			return null;
		}
		if (type.equals("name")) {
			return jdbc.searchBusinessByName(query, sort);
		} else {
			return jdbc.searchBusinessByCategory(query, sort);
		}
	}

	public Business[] searchBusinesses(BusinessId[] businessIds) {
		if (businessIds == null) {
			return null;
		}
		ArrayList<Business> businesses = new ArrayList<Business>();
		HashMap<String, Boolean> seen = new HashMap<String, Boolean>();
		for (int i = 0; i < businessIds.length; i++) {
			if (!seen.containsKey(businessIds[i].getBusinessUid())) {
				Business business = jdbc.searchBusiness(businessIds[i]);
				if (business != null) {
					seen.put(businessIds[i].getBusinessUid(), true);
					businesses.add(business);
				}
			}
		}
		return businesses.toArray(new Business[businesses.size()]);
	}

	public Business searchBusinessByUid(String businessUid) {
		BusinessId businessId = new BusinessId(businessUid);
		return jdbc.searchBusiness(businessId);
	}
}
