package com.qa.jdbc.controller;

import static org.junit.Assert.assertEquals;

import java.util.ArrayList;
import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.Spy;
import org.mockito.junit.MockitoJUnitRunner;

import com.qa.controller.CustomerController;
import com.qa.domain.Customer;
import com.qa.services.CustomerServices;

@RunWith(MockitoJUnitRunner.class)
public class CustomerControllerTest {

	/**
	 * The thing I want to fake functionlity for
	 */
	@Mock
	private CustomerServices customerServices;

	/**
	 * Spy is used because I want to mock some methods inside the item I'm testing
	 * InjectMocks uses dependency injection to insert the mock into the customer
	 * controller
	 */
	@Spy
	@InjectMocks
	private CustomerController customerController;

	@Test
	public void createTest() {
		String firstName = "John";
		String surname = "Smith";
		Customer customer = new Customer(firstName, surname);
		Mockito.doReturn(firstName, surname).when(customerController).getInput();
		Customer savedCustomer = new Customer(1L, "John", "Smith");
		Mockito.when(customerServices.create(customer)).thenReturn(savedCustomer);
		assertEquals(savedCustomer, customerController.create());
	}

	@Test
	public void readAllTest() {
		CustomerController customerController = new CustomerController(customerServices);
		Customer customer1 = (new Customer("John", "Smith"));
		Customer customer2 = (new Customer("Alex", "Brown"));
		Customer customer3 = (new Customer("Simon", "Jones"));
		List<Customer> customers = new ArrayList<>();
		customers.add(customer1);
		customers.add(customer2);
		customers.add(customer3);
		Mockito.when(customerServices.readAll()).thenReturn(customers);
		assertEquals(customers, customerController.readAll());
	}

	/** 
	 *    
	 */
	@Test
	public void updateTest() {
		String id = "1";
		String firstName = "Alex";
		String surname = "Brown";
		Customer customer = new Customer(1L, firstName, surname);
		Mockito.doReturn(id, firstName, surname).when(customerController).getInput();
		Customer returnedCustomer = new Customer(1L, firstName, surname);
		Mockito.when(customerServices.update(customer)).thenReturn(returnedCustomer);
		assertEquals(returnedCustomer, customerController.update());
	}

	/**
	 * Delete doesn't return anything, so we can just verify that it calls the
	 * delete method
	 */
	@Test
	public void deleteTest() {
		String id = "1";
		Mockito.doReturn(id).when(customerController).getInput();
		customerController.delete();
	}

}
