package com.qa.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import org.apache.log4j.Logger;

import com.qa.domain.Item;
import com.qa.domain.Order;
import com.qa.utils.Config;
import com.qa.utils.Utilities;

///**
// * This class is my DAO for the Orders table
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
		LOGGER.info("Database connected!");
	}

	/**
	 * Converts the returns the resultSet as an order.
	 * 
	 * @param resultSet
	 * 
	 */

	Order orderFromResultSet(ResultSet resultSet) {
		try {
			Long orderId = resultSet.getLong("order_id");
			Double orderCost = resultSet.getDouble("cost");
			Long customerId = resultSet.getLong("customer_id");
			Double discount = resultSet.getDouble("discount");
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
		try (Connection conn = DriverManager.getConnection(Config.url, Config.username, Config.password);
				Statement statement = conn.createStatement();) {
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
		try (Connection conn = DriverManager.getConnection(Config.url, Config.username, Config.password);
				Statement statement = conn.createStatement();) {
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
	 * Allows the user to create an item, ignoring ID since that is
	 * auto-incremented. hello
	 */

	@SuppressWarnings("finally")
	public Order create(Order order) {
		try (Connection conn = DriverManager.getConnection(Config.url, Config.username, Config.password);
				Statement statement = conn.createStatement();) {
			statement.executeUpdate(
					"INSERT INTO orders(cost, customer_id, discount) VALUES(" + order.getOrderCost() + ","
							+ order.getCustomerId() + "," + order.getDiscount() + ");",
					Statement.RETURN_GENERATED_KEYS);
			resultSet = statement.getGeneratedKeys();
			resultSet.next();
			order.setOrderId((long) resultSet.getInt(1));

			addItemToOrder(order);

			updateCost(order);
			LOGGER.info("Order created!");

		} catch (Exception e) {
			LOGGER.debug(e.getStackTrace());
			LOGGER.error(e.getMessage());
		} finally {
			try {

				if (resultSet != null)

					resultSet.close();

			} catch (Exception e) {
				LOGGER.debug(e.getStackTrace());
				LOGGER.error(e.getMessage());
			}

		}
		return null;
	}

	public Order addItemToOrder(Order order) {
		try (Connection conn = DriverManager.getConnection(Config.url, Config.username, Config.password);
				Statement statement = conn.createStatement();) {
			for (Item item : order.getItemsInOrder()) {

//			statement.executeUpdate(String.format("INSERT INTO item_order VALUES(null," + orderId+ ","
//					+ item.getItemId() + "," + item.getItemValue() + "," + item.getItemQuantity() + ");"));
				statement.executeUpdate(String.format("INSERT INTO itemorder values(null,'%s','%s','%s','%s');",
						order.getOrderId(), item.getItemId(), item.getItemQuantity(), item.getItemValue()));
			}
		} catch (Exception e) {
			LOGGER.debug(e.getStackTrace());
			LOGGER.error(e.getMessage());

		} finally {
			close();
		}

		return null;

	}

	public Order costCalculator(Order order) {
		try (Connection conn = DriverManager.getConnection(Config.url, Config.username, Config.password);
				Statement statement = conn.createStatement();) {
			resultSet = statement.executeQuery(String.format(
					"SELECT SUM(quantity * sold_cost) FROM itemorder WHERE order_id =" + order.getOrderId() + ";"));
			resultSet.next();
			order.setOrderCost(resultSet.getDouble(1));

		} catch (Exception e) {
			LOGGER.debug(e.getStackTrace());
			LOGGER.error(e.getMessage());

		} finally {
			close();
		}

		return order;

	}

	public Order updateCost(Order order) {
		try (Connection conn = DriverManager.getConnection(Config.url, Config.username, Config.password)) {
			Order orderCost = costCalculator(order);
			if (orderCost.getOrderCost() >= 10000) {

				orderCost.setDiscount(orderCost.getOrderCost() * 0.1);
				orderCost.setOrderCost(orderCost.getOrderCost() * 0.9);

			}

			try (Statement statement = conn.createStatement()) {
				statement.executeUpdate(
						String.format("UPDATE orders SET cost = '%s', discount = '%s' WHERE order_id='%s';",
								orderCost.getOrderCost(), orderCost.getDiscount(), orderCost.getOrderId()));
			}

		} catch (Exception e) {
			LOGGER.debug(e.getStackTrace());
			LOGGER.error(e.getMessage());

		}
		return null;

	}

	/**
	 * Allows the user to read an item from the database.
	 * 
	 * @param itemId
	 * 
	 */

	public Order readOrder(Long orderId) {
		try (Connection conn = DriverManager.getConnection(Config.url, Config.username, Config.password);
				Statement statement = conn.createStatement();) {
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

		try (Connection conn = DriverManager.getConnection(Config.url, Config.username, Config.password);
				Statement statement = conn.createStatement();) {
			Item item = order.getItemsInOrder().get(0);
			statement.executeUpdate(
					String.format("UPDATE item_order set item_quantity = '%s' WHERE order_id='%s' AND item_id = '%s';",
							item.getItemQuantity(), order.getOrderId(), item.getItemId()));
			updateCost(order);
		} catch (Exception e) {
			LOGGER.debug(e.getStackTrace());
			LOGGER.error(e.getMessage());
		}
		return null;
	}

//		try (Connection conn = DriverManager.getConnection(Config.url, Config.username, Config.password);
//				Statement statement = conn.createStatement();) {
//			statement.executeUpdate("UPDATE orders SET cost ='" + order.getOrderCost() + ", discount ="
//					+ order.getDiscount() + ", item_quantity =" + order.getItemsInOrder() + " WHERE order_id ="
//					+ order.getOrderId() + ";");
//			return readOrder(order.getOrderId());
//		} catch (Exception e) {
//			LOGGER.debug(e.getStackTrace());
//			LOGGER.error(e.getMessage());
//		}
//
//		finally {
//			close();
//		}
//
//		return null;
//	}

	/**
	 * Enables the DELETE item functionality.
	 */

	public void delete(long orderId) {
		try (Connection conn = DriverManager.getConnection(Config.url, Config.username, Config.password);
				Statement statement = conn.createStatement();) {
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
	public Order readSingle(long id) {
		// TODO Auto-generated method stub
		return null;
	}

}
