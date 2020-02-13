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

	public List<Order> readAll() {
		List<Order> orders = orderService.readAll();
		for (Order order : orders) {
			LOGGER.info(order.toString());
		}
		return orders;
	}

	public Order create() {
		LOGGER.info("Please enter a customer ID: ");
		Long customerId = Long.parseLong(getInput());
		ArrayList<Item> itemsInOrder = new ArrayList<>();
		Long itemId = 0L;

		while (itemId!=0) {
			LOGGER.info(
					"Please enter the ID of the item you wish to add to your order, or enter 0 to complete your order.");
			itemId = Long.parseLong(getInput());
			if (itemId == 0) {
				break;
			}
			ItemController itemController = new ItemController(itemServices);
			Item item = itemController.readSingle(new Item(itemId));
			LOGGER.info("Please enter how many of this item you want: ");
			item.setItemQuantity(Long.parseLong(getInput()));
			itemsInOrder.add(item);

		}
		LOGGER.info("Order successfully created.\n");
		return orderService.create(new Order(customerId, itemsInOrder));
		
		

	}

	public Order update() {
		LOGGER.info("Please enter the id of the order you would like to update: ");
		Long orderId = Long.valueOf(getInput());
		LOGGER.info("Please enter the id of the item you would like to update: ");
		Long itemId = Long.parseLong(getInput());
		LOGGER.info("Please enter the new item quantity;");
		Long itemQuantity = Long.parseLong(getInput());
		Order order = orderService.update(new Order(orderId, itemId, itemQuantity));
		LOGGER.info("Order successfully updated.\n");
		return order;
	}

	public void delete() {
		LOGGER.info("Please enter the id of the order you would like to delete: ");
		Long orderId = Long.valueOf(getInput());
		orderService.delete(orderId);
		LOGGER.info("Order successfully deleted.\n");
	}

	@Override
	public Order readSingle(Order t) {
		return null;
	}
	
}
