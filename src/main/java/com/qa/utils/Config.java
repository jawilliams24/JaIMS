package com.qa.utils;

/**
 * This is my Config class, which simply declares that there is a root username
 * and password.
 * 
 * @author James Williams
 *
 */

public class Config {
	private Config(String username, String password, String url) {

	}

	private static String username;
	private static String password;
	private static String url = "jdbc:mysql://35.204.131.16:3306/inventory_management_sys";

	public static String getUsername() {
		return username;
	}

	public static void setUsername(String username) {
		Config.username = username;
	}

	public static String getPassword() {
		return password;
	}

	public static void setPassword(String password) {
		Config.password = password;
	}

	public static String getUrl() {
		return url;
	}

	public static void setUrl(String url) {
		Config.url = url;
	}

}
