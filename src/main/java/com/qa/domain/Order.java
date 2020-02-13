package com.qa.domain;

import java.util.ArrayList;

/**
 * This class is to create Orders for my database
 * 
 * @author James Williams
 *
 */


public class Order {

	private Long orderId;
	private float orderCost;
	private Long customerId;
	private Long discount;
	ArrayList<Item> itemsInOrder;
	
	public Order(float orderCost, Long customerId, Long discount) {
		this.orderCost = orderCost;
		this.customerId = customerId;
		this.discount = discount;
	}
	
	public Order(Long orderId, float orderCost, Long customerId, Long discount) {
		this.orderId = orderId;
		this.orderCost = orderCost;
		this.customerId = customerId;
		this.discount = discount;
	}
	
	public Order(Long customerId, ArrayList<Item> itemsInOrder) {
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

	public float getOrderCost() {
		return orderCost;
	}

	public void setOrderCost(float orderCost) {
		this.orderCost = orderCost;
	}

	public Long getCustomerId() {
		return customerId;
	}

	public void setCustomerId(Long customerId) {
		this.customerId = customerId;
	}

	public Long getDiscount() {
		return discount;
	}

	public void setDiscount(Long discount) {
		this.discount = discount;
	}
	
	public String toString() {
		return "id:" + orderId + " order cost:" + orderCost + " customer id:" + customerId + " discount:" + discount;
	}

	public ArrayList<Item> getItemsInOrder() {
		return itemsInOrder;
	}

	public void setItemsInOrder(ArrayList<Item> itemsInOrder) {
		this.itemsInOrder = itemsInOrder;
	}

	
}
