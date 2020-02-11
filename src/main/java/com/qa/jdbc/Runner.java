package com.qa.jdbc;

import java.sql.SQLException;

import org.apache.log4j.Logger;

/**
 * This is the Runner class for my IMS project for QA Consulting.
 * This class simply initialises the IMS.
 * 
 * @author James Williams
 *
 */

public class Runner {

	public static final Logger LOGGER = Logger.getLogger(Runner.class);
	
	public static void main(String[] args) {
		Ims ims = new Ims();
		ims.imsSystem();
	}

}
