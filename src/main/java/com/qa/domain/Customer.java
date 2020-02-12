package com.qa.domain;

/**
 * This class is to create Customers for my database
 * 
 * @author James Williams
 *
 */

public class Customer {

	private Long id;
	private String firstName;
	private String surname;

	/**
	 * Constructor for creating new customers.
	 * @param firstName
	 * @param surname
	 */
	
	public Customer(String firstName, String surname) {
		this.firstName = firstName;
		this.surname = surname;
	}

	/**
	 * Constructor for reading customers.
	 * @param id
	 * @param firstName
	 * @param surname
	 */
	
	public Customer(Long id, String firstName, String surname) {
		this.id = id;
		this.firstName = firstName;
		this.surname = surname;

		/**
		 * Getters and Setters for Customer parameters.
		 * 
		 */
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getSurname() {
		return surname;
	}

	public void setSurname(String surname) {
		this.surname = surname;
	}

	public String toString() {
		return "id:" + id + " first name:" + firstName + " surname:" + surname;
	}

}
