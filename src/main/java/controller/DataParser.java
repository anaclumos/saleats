package controller;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.UnsupportedEncodingException;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonElement;
import com.google.gson.JsonIOException;
import com.google.gson.JsonSyntaxException;

import model.Business;

public class DataParser {
	private static Gson gson = new GsonBuilder().setPrettyPrinting().create();
	private static JdbcDriver jdbc = JdbcDriver.getInstance();
	private static String filename;

	public DataParser(String filename) {
		DataParser.filename = filename;
	}

	public void parse() {
		try {
			JsonElement json = gson.fromJson(new FileReader(filename), JsonElement.class);
			JsonElement business = json.getAsJsonObject().get("businesses");
			Business[] businesses = gson.fromJson(business, Business[].class);
			try {
				jdbc.insertBusinesses(businesses);
			} catch (UnsupportedEncodingException e) {
				e.printStackTrace();
			}
		} catch (JsonSyntaxException e1) {
			e1.printStackTrace();
		} catch (JsonIOException e1) {
			e1.printStackTrace();
		} catch (FileNotFoundException e1) {
			e1.printStackTrace();
		}
	}
}
