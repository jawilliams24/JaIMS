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

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((customerId == null) ? 0 : customerId.hashCode());
		result = prime * result + ((discount == null) ? 0 : discount.hashCode());
		result = prime * result + ((itemsInOrder == null) ? 0 : itemsInOrder.hashCode());
		result = prime * result + ((orderCost == null) ? 0 : orderCost.hashCode());
		result = prime * result + ((orderId == null) ? 0 : orderId.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Order other = (Order) obj;
		if (customerId == null) {
			if (other.customerId != null)
				return false;
		} else if (!customerId.equals(other.customerId))
			return false;
		if (discount == null) {
			if (other.discount != null)
				return false;
		} else if (!discount.equals(other.discount))
			return false;
		if (itemsInOrder == null) {
			if (other.itemsInOrder != null)
				return false;
		} else if (!itemsInOrder.equals(other.itemsInOrder))
			return false;
		if (orderCost == null) {
			if (other.orderCost != null)
				return false;
		} else if (!orderCost.equals(other.orderCost))
			return false;
		if (orderId == null) {
			if (other.orderId != null)
				return false;
		} else if (!orderId.equals(other.orderId))
			return false;
		return true;
	}

}
