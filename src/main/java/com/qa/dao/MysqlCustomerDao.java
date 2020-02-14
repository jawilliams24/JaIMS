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
import com.qa.utils.Config;
import com.qa.utils.Utilities;

/**
 * This class is my DAO for the customers table
 * 
 * @author James Williams
 *
 */

public class MysqlCustomerDao implements Dao<Customer> {

	public static final Logger LOGGER = Logger.getLogger(MysqlCustomerDao.class);

	/**
	 * Connects the program to the database.
	 */

	public MysqlCustomerDao() {
		LOGGER.info("Connecting database...");
		LOGGER.info("Database connected!");

	}

	/**
	 * Converts the returns the resultSet as a customer.
	 * 
	 * @param resultSet
	 * 
	 */

	Customer customerFromResultSet(ResultSet resultSet) {
		try {

			Long id = resultSet.getLong("customer_id");
			String firstName = resultSet.getString("first_name");
			String surname = resultSet.getString("surname");
			return new Customer(id, firstName, surname);
		} catch (SQLException e) {
			LOGGER.debug(e.getStackTrace());
		}
		return null;
	}

	/**
	 * Reads all customers from the database.
	 */

	public List<Customer> readAll() {
		try (Connection conn = DriverManager.getConnection(Config.getUrl(), Config.getUsername(), Config.getPassword());
				Statement statement = conn.createStatement();
				ResultSet resultSet = statement.executeQuery("SELECT * FROM customers");) {

			Utilities util = new Utilities();
			String customerResults = util.resultSetToString(resultSet);
			LOGGER.info(customerResults);

		} catch (Exception e) {
			LOGGER.debug(e.getStackTrace());
		}
		return new ArrayList<>();
	}

	/**
	 * Reads the latest customer from the database.
	 * 
	 */

	public Customer readLatest() {
		try (Connection conn = DriverManager.getConnection(Config.getUrl(), Config.getUsername(), Config.getPassword());
				Statement statement = conn.createStatement();
				ResultSet resultSet = statement
						.executeQuery("SELECT * FROM customers ORDER BY customer_id DESC LIMIT 1");) {

			resultSet.next();
			return customerFromResultSet(resultSet);
		} catch (Exception e) {
			LOGGER.debug(e.getStackTrace());
			LOGGER.error(e.getMessage());
		}
		return null;
	}

	/**
	 * Allows the user to create a customer, ignoring ID since that is
	 * auto-incremented.
	 */

	public Customer create(Customer customer) {
		try (Connection conn = DriverManager.getConnection(Config.getUrl(), Config.getUsername(), Config.getPassword());
				Statement statement = conn.createStatement();) {
			statement.executeUpdate("INSERT INTO customers(first_name, surname) VALUES('" + customer.getFirstName()
					+ "','" + customer.getSurname() + "');");
			return readLatest();
		} catch (Exception e) {
			LOGGER.debug(e.getStackTrace());
			LOGGER.error(e.getMessage());
		}

		return null;
	}

	/**
	 * Allows the user to read a customer from the database.
	 * 
	 * @param id
	 * 
	 */

	public Customer readSingle(long customer) {
		try (Connection conn = DriverManager.getConnection(Config.getUrl(), Config.getUsername(), Config.getPassword());
				Statement statement = conn.createStatement();
				ResultSet resultSet = statement
						.executeQuery("SELECT * FROM customers where customer_id = " + customer);) {

			resultSet.next();
			return customerFromResultSet(resultSet);
		} catch (Exception e) {
			LOGGER.debug(e.getStackTrace());
			LOGGER.error(e.getMessage());
		}

		return null;
	}

	/**
	 * Enables the UPDATE customer functionality.
	 */

	public Customer update(Customer customer) {
		try (Connection conn = DriverManager.getConnection(Config.getUrl(), Config.getUsername(), Config.getPassword());
				Statement statement = conn.createStatement();) {
			statement.executeUpdate("UPDATE customers SET first_name ='" + customer.getFirstName() + "', surname ='"
					+ customer.getSurname() + "' WHERE customer_id =" + customer.getId() + ";");
//			return readSingle(customer.getId());
		} catch (Exception e) {
			LOGGER.debug(e.getStackTrace());
			LOGGER.error(e.getMessage());
		}

		return null;
	}

	/**
	 * Enables the DELETE customer functionality.
	 */

	public void delete(long id) {
		try (Connection conn = DriverManager.getConnection(Config.getUrl(), Config.getUsername(), Config.getPassword());
				Statement statement = conn.createStatement();) {
			statement.executeUpdate("DELETE FROM customers WHERE customer_id = " + id);
		} catch (Exception e) {
			LOGGER.debug(e.getStackTrace());
			LOGGER.error(e.getMessage());
		}

	}

}
