package com.qa.jdbc;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

/**
 * This class is my DAO for the customers table
 * 
 * @author James Williams
 *
 */

public class MysqlCustomerDao implements Dao<Customer> {

	private Connection conn;
	private String url = "jdbc:mysql://35.204.131.16:3306/inventory_management_sys";
	private String username = "root";
	private String password = "root";

	public MysqlCustomerDao() throws SQLException {
		System.out.println("Connecting database...");
		this.conn = DriverManager.getConnection(url, username, password); // this code creates the database connection
																		
		System.out.println("Database connected!");
	}

	public ArrayList<Customer> getAll() {
		ArrayList<Customer> customers = new ArrayList<Customer>();
		try {
			Statement smnt = conn.createStatement();
			ResultSet rs = smnt.executeQuery("SELECT * FROM customers");
			while (rs.next()) { 										  // while there is a next record, go to it
				Long id = rs.getLong("customer_id");
				String firstName = rs.getString("firstName");
				String surname = rs.getString("surname");
				Customer customer = new Customer(id, firstName, surname);
				customers.add(customer); 								  // this adds each customer to the customer list
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return customers;
	}

	public void create(Customer t) {
		try {
			Statement smnt = conn.createStatement();
			System.out.println("moo");
//			smnt.executeQuery("INSERT INTO customers VALUES(null,'Sam','White')");
			smnt.executeUpdate("INSERT INTO customers VALUES(null,'Sam','White');");
			conn.close();

		} catch (Exception e) {
			System.err.println("Got an exception!");
			System.err.println(e.getMessage());
		}
	}

//	public ArrayList<Customer> readAll() {
//		return null;
//	}

	public void update(Customer t) {

	}

	public void delete(int id) {

	}

}
