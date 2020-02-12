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
import com.qa.utils.Config;
import com.qa.utils.Utilities;

///**
// * This class is my DAO for the Items table
// * @author James Williams
// *
// */

public class MysqlItemDao implements Dao<Item> {

	public static final Logger LOGGER = Logger.getLogger(MysqlItemDao.class);

	private Connection conn;
	private Statement statement = null;
	private ResultSet resultSet = null;

	public MysqlItemDao() {
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

	Item itemFromResultSet(ResultSet resultSet) {
		try {
		
		Long itemId = resultSet.getLong("item_id");
		String itemName = resultSet.getString("item_name");
		Long itemValue = resultSet.getLong("item_value");
		Long itemQuantity = resultSet.getLong("item_quantity");
		return new Item(itemId, itemName, itemValue, itemQuantity);
		} catch (SQLException e) {
			LOGGER.debug(e.getStackTrace());
		} finally {
			close();
		}
		return null;
	}

	public List<Item> readAll() {
		try {
			statement = conn.createStatement();
			resultSet = statement.executeQuery("SELECT * FROM items");

			Utilities util = new Utilities();
			String itemResults = util.resultSet_toString(resultSet);
			System.out.println(itemResults);

		} catch (Exception e) {
			LOGGER.debug(e.getStackTrace());
		}

		finally {
			close();
		}
		return new ArrayList<>();
	}

	public Item readLatest() {
		try {
			statement = conn.createStatement();
			resultSet = statement.executeQuery("SELECT * FROM items ORDER BY item_id DESC LIMIT 1");
			resultSet.next();
			return itemFromResultSet(resultSet);
		} catch (Exception e) {
			LOGGER.debug(e.getStackTrace());
			LOGGER.error(e.getMessage());
		}

		finally {
			close();
		}
		return null;
	}

	public Item create(Item item) {
		try {
			statement = conn.createStatement();
			statement.executeUpdate("INSERT INTO items(item_name, item_value, item_quantity) VALUES('" + item.getItemName()
					+ "','" + item.getItemValue() + "','" + item.getItemQuantity() + "');");
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

	public Item readItem(Long itemId) {
		try {
			statement = conn.createStatement();
			resultSet = statement.executeQuery("SELECT * FROM items where item_id = " + itemId);
			resultSet.next();
			return itemFromResultSet(resultSet);
		} catch (Exception e) {
			LOGGER.debug(e.getStackTrace());
			LOGGER.error(e.getMessage());
		}

		finally {
			close();
		}

		return null;
	}

	public Item update(Item item) {
		try {
			statement = conn.createStatement();
			statement.executeUpdate("UPDATE items SET item_name ='" + item.getItemName() + "', item_value ="
					+ item.getItemValue() + ", item_quantity =" + item.getItemQuantity() + " WHERE item_id =" + item.getItemId() + ";");
			return readItem(item.getItemId());
		} catch (Exception e) {
			LOGGER.debug(e.getStackTrace());
			LOGGER.error(e.getMessage());
		}

		finally {
			close();
		}

		return null;
	}

	public void delete(long itemId) {
		try {
			statement = conn.createStatement();
			statement.executeUpdate("DELETE FROM items WHERE item_id = " + itemId);
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
			LOGGER.debug(se2.getStackTrace());
		}
		try {
			if (resultSet != null)

				resultSet.close();
		} catch (SQLException se) {
			LOGGER.debug(se.getStackTrace());
		}

	}

}
