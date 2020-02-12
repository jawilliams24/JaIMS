package com.qa.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import org.apache.log4j.Logger;
import com.qa.domain.Order;
import com.qa.utils.Config;
import com.qa.utils.Utilities;

///**
// * This class is my DAO for the Items table
// * @author James Williams
// *
// */

public class MysqlOrderDao implements Dao<Order> {

	public static final Logger LOGGER = Logger.getLogger(MysqlOrderDao.class);

	private Connection conn;
	private Statement statement = null;
	private ResultSet resultSet = null;

	/**
	 * Connects the program to the database.
	 */
	
	public MysqlOrderDao() {
		LOGGER.info("Connecting database...");
		try {
			this.conn = DriverManager.getConnection(Config.url, Config.username, Config.password);
			LOGGER.info("Database connected!");
		} catch (SQLException e) {
			LOGGER.debug(e.getStackTrace());
		} finally {
			close();
		}
	}

	/**
	 * Converts the returns the resultSet as an order.
	 * @param resultSet
	 * 
	 */
	
	Order orderFromResultSet(ResultSet resultSet) {
		try {
		
		Long orderId = resultSet.getLong("order_id");
		float orderCost = resultSet.getFloat("cost");
		Long customerId = resultSet.getLong("customer_id");
		Long discount = resultSet.getLong("discount");
		return new Order(orderId, orderCost, customerId, discount);
		} catch (SQLException e) {
			LOGGER.debug(e.getStackTrace());
		} finally {
			close();
		}
		return null;
	}

	/**
	 * Reads all items from the database.
	 */
	
	public List<Order> readAll() {
		try {
			statement = conn.createStatement();
			resultSet = statement.executeQuery("SELECT * FROM orders");

			Utilities util = new Utilities();
			String orderResults = util.resultSet_toString(resultSet);
			LOGGER.info(orderResults);

		} catch (Exception e) {
			LOGGER.debug(e.getStackTrace());
		}

		finally {
			close();
		}
		return new ArrayList<>();
	}

	/**
	 * Reads the latest item from the database. 
	 * 
	 */
	
	public Order readLatest() {
		try {
			statement = conn.createStatement();
			resultSet = statement.executeQuery("SELECT * FROM orders ORDER BY order_id DESC LIMIT 1");
			resultSet.next();
			return orderFromResultSet(resultSet);
		} catch (Exception e) {
			LOGGER.debug(e.getStackTrace());
			LOGGER.error(e.getMessage());
		}

		finally {
			close();
		}
		return null;
	}

	/**
	 * Allows the user to create an item, ignoring ID since that is auto-incremented.
	 */
	
	public Order create(Order order) {
		try {
			statement = conn.createStatement();
			statement.executeUpdate("INSERT INTO orders(customer_id, cost, discount) VALUES('" 
					+ order.getCustomerId()+ order.getOrderCost() + "," + order.getDiscount() + ");");
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

	/**
	 * Allows the user to read an item from the database.
	 * @param itemId
	 * 
	 */
	
	public Order readOrder(Long orderId) {
		try {
			statement = conn.createStatement();
			resultSet = statement.executeQuery("SELECT * FROM orders WHERE order_id = " + orderId);
			resultSet.next();
			return orderFromResultSet(resultSet);
		} catch (Exception e) {
			LOGGER.debug(e.getStackTrace());
			LOGGER.error(e.getMessage());
		}

		finally {
			close();
		}

		return null;
	}

	/**
	 * Enables the UPDATE item functionality.
	 */
	
	public Order update(Order order) {
		try {
			statement = conn.createStatement();
			statement.executeUpdate("UPDATE orders SET cost ='" + order.getOrderCost() + 
					", discount =" + order.getDiscount() + ", item_quantity =" + order.getItemsInOrder() + " WHERE order_id =" + order.getOrderId() + ";");
			return readOrder(order.getOrderId());
		} catch (Exception e) {
			LOGGER.debug(e.getStackTrace());
			LOGGER.error(e.getMessage());
		}

		finally {
			close();
		}

		return null;
	}

	/**
	 * Enables the DELETE item functionality.
	 */
	
	public void delete(long orderId) {
		try {
			statement = conn.createStatement();
			statement.executeUpdate("DELETE FROM orders WHERE order_id = " + orderId);
		} catch (Exception e) {
			LOGGER.debug(e.getStackTrace());
			LOGGER.error(e.getMessage());
		}

		finally {
			close();
		}

	}

	/**
	 * Useful close method to automatically close the connection when finished.
	 */
	
	public void close() {
		try {
			if (statement != null)
				statement.close();
		} catch (SQLException se2) {
			LOGGER.debug(se2.getStackTrace());
		}
		try {
			if (resultSet != null)

				resultSet.close();
		} catch (SQLException se) {
			LOGGER.debug(se.getStackTrace());
		}

	}

	@Override
	public Order readSingle(Order t) {
		return null;
	}

}
