package com.qa.controller;

import java.util.List;

import org.apache.log4j.Logger;

import com.qa.domain.Order;
import com.qa.services.CrudServices;
import com.qa.utils.Utilities;

/**
 * This class interacts with the user to give them instructions and then feeds
 * back to them once they have completed their actions.
 * 
 * @author James Williams
 *
 */



public class OrderController implements CrudController<Order>{

	public static final Logger LOGGER = Logger.getLogger(OrderController.class);
	
	private CrudServices<Order> orderService;
	
	public OrderController(CrudServices<Order> orderService) {
		this.orderService = orderService;
	}
	
	String getInput() {
		return Utilities.getInput();
	}
	
	public List<Order> readAll() {
		List<Order> orders = orderService.readAll();
		for(Order order: orders) {
			LOGGER.info(order.toString());
		}
		return orders;
	}

	public Order create() {
		LOGGER.info("Please enter a order cost");
		float orderCost = Float.parseFloat(getInput());
		LOGGER.info("Please enter a customer id");
		Long customerId = Long.parseLong(getInput());
		LOGGER.info("Please enter a discount");
		Long discount = Long.parseLong(getInput());
		Order order = orderService.create(new Order(orderCost, customerId, discount));
		LOGGER.info("Order created");
		return order;
	}

	public Order update() {
		LOGGER.info("Please enter the id of the order you would like to update");
		Long orderId = Long.valueOf(getInput());
		LOGGER.info("Please enter a order cost");
		float orderCost = Float.parseFloat(getInput());
		LOGGER.info("Please enter a customer id");
		Long customerId = Long.parseLong(getInput());
		LOGGER.info("Please enter a discount");
		Long discount = Long.parseLong(getInput());
		Order order = orderService.update(new Order(customerId, orderCost, customerId, discount));
		LOGGER.info("Order updated");
		return order;
	}

	public void delete() {
		LOGGER.info("Please enter the id of the order you would like to delete");
		Long orderId = Long.valueOf(getInput());
		orderService.delete(orderId);
	}
	
}
