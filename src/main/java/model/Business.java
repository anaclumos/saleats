package model;

public class Business {
	private String alias;
	private String id;
	private String name;
	private String image_url;
	private boolean is_closed;
	private String url;
	private int review_count;
	private Category[] categories;
	private double rating;
	private Coordinate coordinates;
	private String[] transactions;
	private String price;
	private Location location;
	private String phone;
	private String display_phone;
	private String display_address;
	private double distance;

	public Business() {
	}

	public Business(String id, String alias, String name, String image_url, boolean is_closed, String url,
			int review_count, Category[] categories, double rating, Coordinate coordinates, String[] transactions,
			String price, Location location, String phone, String display_phone, double distance) {
		this.id = id;
		this.alias = alias;
		this.name = name;
		this.image_url = image_url;
		this.is_closed = is_closed;
		this.url = url;
		this.review_count = review_count;
		this.categories = categories;
		this.rating = rating;
		this.coordinates = coordinates;
		this.transactions = transactions;
		this.price = price;
		this.location = location;
		this.phone = phone;
		this.display_phone = display_phone;
		this.distance = distance;
	}

	public String getId() {
		return id;
	}

	public String getAlias() {
		return alias;
	}

	public String getName() {
		return name;
	}

	public String getImage() {
		return image_url;
	}

	public boolean isClosed() {
		return is_closed;
	}

	public String getUrl() {
		return url;
	}

	public int getReviewCount() {
		return review_count;
	}

	public Category[] getCategories() {
		return categories;
	}

	public String getStringifiedCategories() {
		String[] elements = new String[categories.length];
		for (int i = 0; i < categories.length; i++) {
			elements[i] = categories[i].toString();
		}
		return String.join(", ", elements);
	}

	public double getRating() {
		return rating;
	}

	public Coordinate getCoordinates() {
		return coordinates;
	}

	public String[] getTransactions() {
		return transactions;
	}

	public String getPrice() {
		return price;
	}

	public Location getLocation() {
		return location;
	}

	public String getPhone() {
		return phone;
	}

	public String getDisplayPhone() {
		return display_phone;
	}

	public double getDistance() {
		return distance;
	}

	public String getDisplayAddress() {
		return display_address;
	}

	public void setId(String id) {
		this.id = id;
	}

	public void setAlias(String alias) {
		this.alias = alias;
	}

	public void setName(String name) {
		this.name = name;
	}

	public void setImage(String image_url) {
		this.image_url = image_url;
	}

	public void setClosed(boolean is_closed) {
		this.is_closed = is_closed;
	}

	public void setUrl(String url) {
		this.url = url;
	}

	public void setReviewCount(int review_count) {
		this.review_count = review_count;
	}

	public void setCategories(Category[] categories) {
		this.categories = categories;
	}

	public void setRating(double rating) {
		this.rating = rating;
	}

	public void setCoordinates(Coordinate coordinates) {
		this.coordinates = coordinates;
	}

	public void setTransactions(String[] transactions) {
		this.transactions = transactions;
	}

	public void setPrice(String price) {
		this.price = price;
	}

	public void setLocation(Location location) {
		this.location = location;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	public void setDisplayPhone(String display_phone) {
		this.display_phone = display_phone;
	}

	public void setDistance(double distance) {
		this.distance = distance;
	}

	public void setDisplayAddress(String display_address) {
		this.display_address = display_address;
	}

	// toString
	@Override
	public String toString() {
		return "Business [id=" + id + ", alias=" + alias + ", name=" + name + ", image_url=" + image_url
				+ ", is_closed=" + is_closed + ", url=" + url + ", review_count=" + review_count + ", categories="
				+ categories + ", rating=" + rating + ", coordinates=" + coordinates + ", transactions=" + transactions
				+ ", price=" + price + ", location=" + location + ", phone=" + phone + ", display_phone="
				+ display_phone + ", distance=" + distance + "]";
	}
}
