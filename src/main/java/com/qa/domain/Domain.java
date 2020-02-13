package com.qa.domain;

import org.apache.log4j.Logger;

import com.qa.utils.Utilities;

/**
 * This enum is for the Domain, which asks the user which entity they'd like to
 * interact with.
 * 
 * @author James Williams
 *
 */

public enum Domain {

	CUSTOMER("Information about customers"), ITEM("Individual Items"), ORDER("Purchases of items"),
	STOP("To close the application");

	public static final Logger LOGGER = Logger.getLogger(Domain.class);

	private String description;

	private Domain(String description) {
		this.description = description;
	}

	private String description() {
		return this.name() + ": " + this.description;
	}

	public static void printDomains() {
		for (Domain domain : Domain.values()) {
			LOGGER.info(domain.description());
		}
	}

	public static Domain getDomain() {
		Domain domain;
		while (true) {
			try {
				domain = Domain.valueOf(Utilities.getInput().toUpperCase());
				break;
			} catch (IllegalArgumentException e) {
				LOGGER.error("Invalid selection please try again");
			}
		}
		return domain;
	}


	public void setDescription(String description) {
		this.description = description;
	}

}
