package com.qa.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import com.qa.domain.Customer;
import com.qa.utils.Utilities;

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

	public MysqlCustomerDao() {
		System.out.println("Connecting database...");
		try {
			this.conn = DriverManager.getConnection(url, username, password);
			System.out.println("Database connected!");
		} catch (SQLException e) {
			e.printStackTrace();

		}
	}

	public ArrayList<Customer> readAll() {
		ArrayList<Customer> customers = new ArrayList<Customer>();
		try {
			Statement smnt = conn.createStatement();
			ResultSet rs = smnt.executeQuery("SELECT * FROM customers");
//			while (rs.next()) { 										  // while there is a next record, go to it
//				Long id = rs.getLong("customer_id");
//				String firstName = rs.getString("first_name");
//				String surname = rs.getString("surname");
//				Customer customer = new Customer(id, firstName, surname);
//				customers.add(customer); // this adds each customer to the customer list
			Utilities util = new Utilities();
			String customerResults = util.resultSet_toString(rs);
			System.out.println(customerResults);

			// }
		} catch (Exception e) {
			e.printStackTrace();
		}

		return null;
	}

	public void create(Customer customer) {
		try {
			Statement smnt = conn.createStatement();
			smnt.executeUpdate("INSERT INTO customers(first_name,surname) VALUES('" + customer.getFirstName() + "','"
					+ customer.getSurname() + "');");
			conn.close();

		} catch (Exception e) {
			System.err.println("Got an exception!");
			System.err.println(e.getMessage());
		}
	}

	public void update(Customer t) {
		try {

			Statement smnt = conn.createStatement();
			smnt.executeUpdate("UPDATE customers SET customer_name= where customer_name=;");
			conn.close();

		} catch (Exception e) {
			System.err.println("Got an exception!");
			System.err.println(e.getMessage());

		}
	}

	public void delete(int id) {
		try {
			Statement smnt = conn.createStatement();
			smnt.executeUpdate("DELETE FROM customers WHERE ");
			conn.close();

		} catch (Exception e) {
			System.err.println("Got an exception!");
			System.err.println(e.getMessage());
		}
	}

}
