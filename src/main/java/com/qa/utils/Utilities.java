package com.qa.utils;

import java.util.Scanner;

public class Utilities {
	
	public static String getInput() {
		@SuppressWarnings("resource")
		Scanner scanner = new Scanner(System.in);
		return scanner.nextLine();
	}


}
