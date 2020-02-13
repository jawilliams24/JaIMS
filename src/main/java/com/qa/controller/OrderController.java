package com.qa.controller;

import java.util.ArrayList;
import java.util.List;

import org.apache.log4j.Logger;

import com.qa.domain.Item;
import com.qa.domain.Order;
import com.qa.services.CrudServices;
import com.qa.services.ItemServices;
import com.qa.dao.MysqlItemDao;
import com.qa.utils.Utilities;

/**
 * This class interacts with the user to give them instructions and then feeds
 * back to them once they have completed their actions.
 * 
 * @author James Williams
 *
 */

public class OrderController implements CrudController<Order> {

	public static final Logger LOGGER = Logger.getLogger(OrderController.class);

	private CrudServices<Order> orderService;
	private CrudServices<Item> itemServices;

	public OrderController(CrudServices<Order> orderService) {
		this.orderService = orderService;
		this.itemServices = new ItemServices(new MysqlItemDao());
	}

	String getInput() {
		return Utilities.getInput();
	}

	/**
	 * This method reads all the orders in the database.
	 */

	public List<Order> readAll() {
		List<Order> orders = orderService.readAll();
		for (Order order : orders) {
			LOGGER.info(order.toString());
		}
		return orders;
	}

	/**
	 * This method is to allow users to create orders in the system.
	 */

	public Order create() {
		LOGGER.info("Please enter a customer ID: ");
		Long customerId = Long.parseLong(getInput());
		ArrayList<Item> itemsInOrder = new ArrayList<>();
		


		while (true) {
			Long itemId = 0L;
			LOGGER.info(
					"Please enter the ID of the item you wish to add to your order, or enter 0 to complete your order.");
			itemId = Long.parseLong(getInput());
			if (itemId == 0) {
				break;
			}
			ItemController itemController = new ItemController(itemServices);
			Item item = itemController.readSingle(itemId);
			LOGGER.info("Please enter how many of this item you want: ");
			Long thing = Long.parseLong(getInput());
			item.setItemQuantity(thing);
			itemsInOrder.add(item);

		}
		LOGGER.info("Order successfully created.\n");
		return orderService.create(new Order(customerId, itemsInOrder));

	}

	public Order update(Order order) {
		return orderService.update(order);

	}

	public void delete() {
		LOGGER.info("Please enter the id of the order you would like to delete: ");
		Long orderId = Long.valueOf(getInput());
		orderService.delete(orderId);
		LOGGER.info("Order successfully deleted.\n");
	}

	@Override
	public Order readSingle(long t) {
		return null;
	}

	@Override
	public Order update() {
		// TODO Auto-generated method stub
		return null;
	}

}
