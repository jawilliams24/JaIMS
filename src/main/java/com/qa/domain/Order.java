package com.qa.domain;

import java.util.List;

/**
 * This class is to create Orders for my database
 * 
 * @author James Williams
 *
 */

public class Order {

	private Long orderId;
	private Double orderCost;
	private Long customerId;
	private Double discount;
	List<Item> itemsInOrder;

	public Order(Double orderCost, Long customerId, Double discount) {
		this.orderCost = orderCost;
		this.customerId = customerId;
		this.discount = discount;
	}

	public Order(Long orderId, Double orderCost, Long customerId, Double discount) {
		this.orderId = orderId;
		this.orderCost = orderCost;
		this.customerId = customerId;
		this.discount = discount;
	}

	public Order(Long customerId, List<Item> itemsInOrder) {
		this.customerId = customerId;
		this.itemsInOrder = itemsInOrder;
	}

	public Order(Long orderId) {
		this.orderId = orderId;
	}

	public Long getOrderId() {
		return orderId;
	}

	public void setOrderId(Long orderId) {
		this.orderId = orderId;
	}

	public Double getOrderCost() {
		return orderCost;
	}

	public void setOrderCost(Double orderCost) {
		this.orderCost = orderCost;
	}

	public Long getCustomerId() {
		return customerId;
	}

	public void setCustomerId(Long customerId) {
		this.customerId = customerId;
	}

	public Double getDiscount() {
		return discount;
	}

	public void setDiscount(Double discount) {
		this.discount = discount;
	}

	public String toString() {
		return "id:" + orderId + " order cost:" + orderCost + " customer id:" + customerId + " discount:" + discount;
	}

	public List<Item> getItemsInOrder() {
		return itemsInOrder;
	}

	public void setItemsInOrder(List<Item> itemsInOrder) {
		this.itemsInOrder = itemsInOrder;
	}

}
