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

	private Connection conn;
	private Statement statement = null;
	private ResultSet resultSet = null;

	public MysqlCustomerDao() {
		LOGGER.info("Connecting database...");
		try {
			this.conn = DriverManager.getConnection(Config.url, Config.username, Config.password);
			LOGGER.info("Database connected!");
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			close();
		}
	}

	Customer customerFromResultSet(ResultSet resultSet) {
		try {
		
		Long id = resultSet.getLong("customer_id");
		String firstName = resultSet.getString("first_name");
		String surname = resultSet.getString("surname");
		return new Customer(id, firstName, surname);
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			close();
		}
		return null;
	}

	public List<Customer> readAll() {
		try {
			statement = conn.createStatement();
			resultSet = statement.executeQuery("SELECT * FROM customers");

			Utilities util = new Utilities();
			String customerResults = util.resultSet_toString(resultSet);
			System.out.println(customerResults);

		} catch (Exception e) {
			e.printStackTrace();
		}

		finally {
			close();
		}
		return new ArrayList<>();
	}

	public Customer readLatest() {
		try {
			statement = conn.createStatement();
			resultSet = statement.executeQuery("SELECT * FROM customers ORDER BY customer_id DESC LIMIT 1");
			resultSet.next();
			return customerFromResultSet(resultSet);
		} catch (Exception e) {
			LOGGER.debug(e.getStackTrace());
			LOGGER.error(e.getMessage());
		}

		finally {
			close();
		}
		return null;
	}

	public Customer create(Customer customer) {
		try {
			statement = conn.createStatement();
			statement.executeUpdate("INSERT INTO customers(first_name, surname) VALUES('" + customer.getFirstName()
					+ "','" + customer.getSurname() + "');");
			return readLatest();
		} catch (Exception e) {
			LOGGER.debug(e.getStackTrace());
			LOGGER.error(e.getMessage());
		}

		finally {
			close();
		}

		return null;
	}

	public Customer readCustomer(Long id) {
		try {
			statement = conn.createStatement();
			resultSet = statement.executeQuery("SELECT * FROM customers where customer_id = " + id);
			resultSet.next();
			return customerFromResultSet(resultSet);
		} catch (Exception e) {
			LOGGER.debug(e.getStackTrace());
			LOGGER.error(e.getMessage());
		}

		finally {
			close();
		}

		return null;
	}

	public Customer update(Customer customer) {
		try {
			statement = conn.createStatement();
			statement.executeUpdate("UPDATE customers SET first_name ='" + customer.getFirstName() + "', surname ='"
					+ customer.getSurname() + "' WHERE customer_id =" + customer.getId() + ";");
			return readCustomer(customer.getId());
		} catch (Exception e) {
			LOGGER.debug(e.getStackTrace());
			LOGGER.error(e.getMessage());
		}

		finally {
			close();
		}

		return null;
	}

	public void delete(long id) {
		try {
			statement = conn.createStatement();
			statement.executeUpdate("DELETE FROM customers WHERE customer_id = " + id);
		} catch (Exception e) {
			LOGGER.debug(e.getStackTrace());
			LOGGER.error(e.getMessage());
		}

		finally {
			close();
		}

	}

	public void close() {
		try {
			if (statement != null)
				statement.close();
		} catch (SQLException se2) {
			se2.printStackTrace();
		}
		try {
			if (resultSet != null)

				resultSet.close();
		} catch (SQLException se) {
			se.printStackTrace();
		}

	}

}
