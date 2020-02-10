package com.qa.utils;

import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.util.Scanner;

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
