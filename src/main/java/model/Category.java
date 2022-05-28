package model;

public class Category {
	private String alias;
	private String title;

	public Category(String title) {
		this.title = title;
	}

	public Category(String alias, String title) {
		this.alias = alias;
		this.title = title;
	}

	public String getAlias() {
		return alias;
	}

	public String getTitle() {
		return title;
	}

	@Override
	public String toString() {
		return title;
	}
}