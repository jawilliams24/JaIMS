package com.qa.jdbc;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

/**
 * This class is my DAO for the Customers table
 * @author James Williams
 *
 */

public class MysqlCustomerDao implements Dao<Customer> {
	
	private Connection conn;
	private String url = "jdbc:mysql://35.204.131.16:3306/IMS";
	private String username = "root";
	private String password = "root";
	

	public MysqlCustomerDao() throws SQLException {
		System.out.println("Connecting database...");
		this.conn = DriverManager.getConnection(url,username,password);		//this bit of code creates the database connection
		System.out.println("Database connected!");
	} 
	
	public ArrayList<Customer> getAll() {
	ArrayList<Customer> customers = new ArrayList<Customer>();
	try {
		Statement smnt = conn.createStatement();
		ResultSet rs = smnt.executeQuery("SELECT * FROM Customers");
		while(rs.next()) {													//while there is a next record, go to it
			Long id = rs.getLong("id");
			String firstName = rs.getString("firstName");
			String surname = rs.getString("surname");
			Customer customer = new Customer(id, firstName, surname);
			customers.add(customer);										//this adds each customer to the customer list
		}
	} catch (Exception e) {
		e.printStackTrace();
	} 
	return customers;
	}
	
	
	public void create(Customer t) {
		
	}

	public ArrayList<Customer> readAll() {
		return null;
	}

	public void update(Customer t) {
		
	}

	public void delete(int id) {
		
	}
	

}
