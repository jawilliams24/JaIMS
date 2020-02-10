package com.qa.controller;

import org.apache.log4j.Logger;

import com.qa.domain.Customer;
import com.qa.services.CrudServices;
import com.qa.utils.Utilities;

public class CustomerController implements CrudController<Customer>{

	public static final Logger LOGGER = Logger.getLogger(CustomerController.class);
	
	private CrudServices<Customer> customerService;
	
	public CustomerController(CrudServices<Customer> customerService) {
		this.customerService = customerService;
	}
	
	public void readAll() {
		for(Customer customer: customerService.readAll()) {
			LOGGER.info(customer.toString());
		}
	}

	public void create() {
		LOGGER.info("Please enter a first name");
		String firstName = Utilities.getInput();
		LOGGER.info("Please enter a surname");
		String surname = Utilities.getInput();
		customerService.create(new Customer(firstName, surname));
		LOGGER.info("Customer created");
	}

	public void update() {
		
	}

	public void delete() {
		
	}
	
}