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

	/**
	 * Connects the program to the database.
	 */

	public MysqlItemDao() {
		LOGGER.info("Connecting database...");
		LOGGER.info("Database connected!");
	}

	/**
	 * Converts the returns the resultSet as an item.
	 * 
	 * @param resultSet
	 * @throws SQLException
	 * 
	 */

	Item itemFromResultSet(ResultSet resultSet) throws SQLException {
		Long itemId = resultSet.getLong("item_id");
		String itemName = resultSet.getString("item_name");
		Long itemValue = resultSet.getLong("item_value");
		return new Item(itemId, itemName, itemValue);
	}

	/**
	 * Reads all items from the database.
	 */

	public List<Item> readAll() {
		try (Connection conn = DriverManager.getConnection(Config.url, Config.username, Config.password);
				Statement statement = conn.createStatement();
				ResultSet resultSet = statement.executeQuery("SELECT * FROM items");) {
			ArrayList<Item> items = new ArrayList<>();
			while (resultSet.next()) {
				Item item = itemFromResultSet(resultSet);
				items.add(item);
				LOGGER.info(item);
			}
			return items;
		} catch (SQLException e) {
			for (StackTraceElement ele : e.getStackTrace()) {
				LOGGER.debug(ele.toString());
			}
			LOGGER.error(e.getMessage());
		}
		return null;
	}

	/**
	 * Reads the latest item from the database.
	 * 
	 */

	public Item readLatest() {
		try (Connection conn = DriverManager.getConnection(Config.url, Config.username, Config.password);
				Statement statement = conn.createStatement();
				ResultSet resultSet = statement.executeQuery("SELECT * FROM items ORDER BY item_id DESC LIMIT 1");) {
			resultSet.next();
			Item item = itemFromResultSet(resultSet);
			return item;

		} catch (SQLException e) {
			for (StackTraceElement ele : e.getStackTrace()) {
				LOGGER.debug(ele.toString());
			}
			LOGGER.error(e.getMessage());
		}
		return null;
	}

	/**
	 * Allows the user to create an item, ignoring ID since that is
	 * auto-incremented.
	 */

	public Item create(Item item) {
		try (Connection conn = DriverManager.getConnection(Config.url, Config.username, Config.password);
				Statement statement = conn.createStatement();) {
			statement.executeUpdate("INSERT INTO items(item_name, item_value) VALUES('" + item.getItemName() + "',"
					+ item.getItemValue() + ");");
			return readLatest();
		} catch (SQLException e) {
			for (StackTraceElement ele : e.getStackTrace()) {
				LOGGER.debug(ele.toString());
			}
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

	public Item readSingle(long itemId) {
		try (Connection conn = DriverManager.getConnection(Config.url, Config.username, Config.password);
				Statement statement = conn.createStatement();
				ResultSet resultSet = statement.executeQuery("SELECT * FROM items WHERE item_id = " + itemId + ";");) {
			resultSet.next();
			Item item = itemFromResultSet(resultSet);
			return item;

		} catch (SQLException e) {
			for (StackTraceElement ele : e.getStackTrace()) {
				LOGGER.debug(ele.toString());
			}
			LOGGER.error(e.getMessage());
		}
		return null;
	}

	/**
	 * Enables the UPDATE item functionality.
	 */

	public Item update(Item item) {
		
		try (Connection conn = DriverManager.getConnection(Config.url, Config.username, Config.password);
				Statement statement = conn.createStatement();) {
			statement.executeUpdate("UPDATE items SET item_name ='" + item.getItemName() + "', item_value ="
					+ item.getItemValue() + " WHERE item_id =" + item.getItemId() + ";");
			return readSingle(item.getItemId());
		} catch (SQLException e) {
			for (StackTraceElement ele : e.getStackTrace()) {
				LOGGER.debug(ele.toString());
			}
			LOGGER.error(e.getMessage());
		}
		return null;
	}

	/**
	 * Enables the DELETE item functionality.
	 */

	public void delete(long itemId) {
		try (Connection conn = DriverManager.getConnection(Config.url, Config.username, Config.password);
				Statement statement = conn.createStatement();) {
			statement.executeUpdate("DELETE FROM items WHERE item_id = " + itemId);
		} catch (SQLException e) {
			for (StackTraceElement ele : e.getStackTrace()) {
				LOGGER.debug(ele.toString());
			}
			LOGGER.error(e.getMessage());
		}
	}



}
