package model;

import java.util.Arrays;

public class Location {

	private String address1;
	private String address2;
	private String address3;
	private String city;
	private String zip_code;
	private String country;
	private String state;
	private String[] display_address;

	public Location(String address1, String address2, String address3, String city, String zip_code, String country,
			String state, String[] display_address) {
		this.address1 = address1;
		this.address2 = address2;
		this.address3 = address3;
		this.city = city;
		this.zip_code = zip_code;
		this.country = country;
		this.state = state;
		this.display_address = display_address;
	}

	public String getAddress1() {
		return address1;
	}

	public String getAddress2() {
		return address2;
	}

	public String getAddress3() {
		return address3;
	}

	public String getCity() {
		return city;
	}

	public String getZip_code() {
		return zip_code;
	}

	public String getCountry() {
		return country;
	}

	public String getState() {
		return state;
	}

	public String[] getDisplayAddress() {
		return display_address;
	}

	@Override
	public String toString() {
		return "Location [address1=" + address1 + ", address2=" + address2 + ", address3=" + address3 + ", city=" + city
				+ ", zip_code=" + zip_code + ", country=" + country + ", state=" + state + ", display_address="
				+ Arrays.toString(display_address) + "]";
	}

}
