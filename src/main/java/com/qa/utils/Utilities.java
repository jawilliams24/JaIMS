package com.qa.utils;

import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.util.Scanner;

/**
 * In this Utilities class, I have set out a simple scanner method to
 * collect a user input and use that to allow them to perform commands
 * in my IMS.
 * Also in here is a ResultSet String converter to make the results of a
 * Read function in SQL more pleasing on the eye.
 * 
 * @author James Williams
 *
 */

public class Utilities {
	
	public static String getInput() {
		@SuppressWarnings("resource")
		Scanner scanner = new Scanner(System.in);
		return scanner.nextLine();
	}

	public String resultSet_toString(ResultSet rs){
		String Result = "";
		try {
			ResultSetMetaData meta = rs.getMetaData();
			while(rs.next()) {
				String row = "";
				for (int i = 1; i <=  meta.getColumnCount(); i++) {
					row +=  meta.getColumnLabel(i) + " = " + rs.getString(i) + " ";
				}
				Result += "\n" + row ;
			}
		}
		catch(SQLException e) {
			Result = "null";
		}
		return Result;
	}

}
	


