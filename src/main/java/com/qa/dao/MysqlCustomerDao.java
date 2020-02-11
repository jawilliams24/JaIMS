package com.qa.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import org.apache.log4j.Logger;

import com.qa.domain.Customer;
import com.qa.utils.Utilities;

/**
 * This class is my DAO for the customers table
 * 
 * @author James Williams
 *
 */

public class MysqlCustomerDao implements Dao<Customer> {

	public static final Logger LOGGER = Logger.getLogger(MysqlCustomerDao.class);
	
	private Connection conn;
	private String url = "jdbc:mysql://35.204.131.16:3306/inventory_management_sys";
	private String username = "root";
	private String password = "Wg04VKLX392CEU";

	public MysqlCustomerDao() {
		System.out.println("Connecting database...");
		try {
			this.conn = DriverManager.getConnection(url, username, password);
			System.out.println("Database connected!");
		} catch (SQLException e) {
			e.printStackTrace();

		}
	}
	
	Customer customerFromResultSet(ResultSet resultSet) throws SQLException {
		Long id = resultSet.getLong("id");
		String firstName = resultSet.getString("first_name");
		String surname = resultSet.getString("surname");
		return new Customer(id, firstName, surname);
	}
	public List<Customer> readAll() {
		try {
			Statement smnt = conn.createStatement();
			ResultSet rs = smnt.executeQuery("SELECT * FROM customers");

			Utilities util = new Utilities();
			String customerResults = util.resultSet_toString(rs);
			System.out.println(customerResults);

		} catch (Exception e) {
			e.printStackTrace();
		}

		return new ArrayList<>();
	}

	public Customer readLatest() {
		try {
			Statement statement = conn.createStatement();
			ResultSet resultSet = statement.executeQuery("SELECT FROM customers ORDER BY id DESC LIMIT 1");
			resultSet.next();
			return customerFromResultSet(resultSet);
		} catch (Exception e) {
			LOGGER.debug(e.getStackTrace());
			LOGGER.error(e.getMessage());
		}
		return null;
	}
	
	public Customer create(Customer customer) {
		try {
			Statement statement = conn.createStatement();
			statement.executeUpdate("INSERT INTO customers(first_name, surname) VALUES('" + customer.getFirstName()
					+ "','" + customer.getSurname() + "');");
			return readLatest();
		} catch (Exception e) {
			LOGGER.debug(e.getStackTrace());
			LOGGER.error(e.getMessage());
		}
		return null;
	}

	public Customer readCustomer(Long id) {
		try	 {
			Statement statement = conn.createStatement();
			ResultSet resultSet = statement.executeQuery("SELECT FROM customers where id = "+id);
			resultSet.next();
			return customerFromResultSet(resultSet);
		} catch (Exception e) {
			LOGGER.debug(e.getStackTrace());
			LOGGER.error(e.getMessage());
		}
		return null;
	}
	
	public Customer update(Customer t) {
		try {

			Statement smnt = conn.createStatement();
			smnt.executeUpdate("UPDATE customers SET customer_name= where customer_name=;");
			conn.close();

		} catch (Exception e) {
			System.err.println("Got an exception!");
			System.err.println(e.getMessage());

		}
		return null;
	}
	@Override
	

	public void delete(long id) {
		try  {
			Statement statement = conn.createStatement();
			statement.executeUpdate("delete from customers where id = " + id);
		} catch (Exception e) {
			LOGGER.debug(e.getStackTrace());
			LOGGER.error(e.getMessage());
		}
	}

}
