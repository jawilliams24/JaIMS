package com.qa.utils;

import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.util.Scanner;
import org.apache.log4j.Logger;

/**
 * In this Utilities class, I have set out a simple scanner method to collect a
 * user input and use that to allow them to perform commands in my IMS. Also in
 * here is a ResultSet String converter to make the results of a Read function
 * in SQL more pleasing on the eye.
 * 
 * @author James Williams
 *
 */

public class Utilities {

	public static final Logger LOGGER = Logger.getLogger(Utilities.class);

	public static String getInput() {
		@SuppressWarnings("resource")
		Scanner scanner = new Scanner(System.in);
		return scanner.nextLine();
	}

	public String resultSetToString(ResultSet rs) {
		String result = "";
		try {
			ResultSetMetaData meta = rs.getMetaData();
			while (rs.next()) {
				String row = "";
				for (int i = 1; i <= meta.getColumnCount(); i++) {
					row += meta.getColumnLabel(i) + " = " + rs.getString(i) + " ";
				}
				result += "\n" + row;
			}
		} catch (SQLException e) {
			result = "null";
		}
		return result;
	}

}
