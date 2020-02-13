package com.qa.jdbc.domain;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertTrue;

import org.junit.Before;
import org.junit.Test;

import com.qa.domain.Item;
import com.qa.domain.Order;

public class OrderTest {

	private Order order;
	private Order other;

	@Before
	public void setUp() {
		order = new Order(1L, 20D, 12L, 20D);
		other = new Order(1L, 20D, 12L, 20D);
	}

	@Test
	public void settersTest() {
		assertNotNull(order.getOrderId());
		assertNotNull(order.getOrderCost());
		assertNotNull(order.getCustomerId());
		assertNotNull(order.getDiscount());

		order.setOrderId(null);
		assertNull(order.getOrderId());
		order.setOrderCost(null);
		assertNull(order.getOrderCost());
		order.setCustomerId(null);
		assertNull(order.getCustomerId());
		order.setDiscount(null);
		assertNull(order.getDiscount());

	}

	@Test
	public void equalsWithNull() {
		assertFalse(order.equals(null));
	}

	@Test
	public void equalsWithDifferentObject() {
		assertFalse(order.equals(new Object()));
	}

	@Test
	public void createItemWithId() {
		assertEquals(1L, order.getOrderId(), 0);
		assertEquals(20D, order.getOrderCost(), 0);
		assertEquals(12L, order.getCustomerId(), 0);
		assertEquals(20D, order.getDiscount(), 0);
	}

	@Test
	public void checkEquality() {
		assertTrue(order.equals(order));
	}

	@Test
	public void checkEqualityBetweenDifferentObjects() {
		assertTrue(order.equals(other));
	}

	@Test
	public void orderCostNullButOtherOrderCostNotNull() {
		order.setOrderCost(null);
		assertFalse(order.equals(other));
	}

	@Test
	public void itemOrderCostsNotEqual() {
		other.setOrderCost(20D);
		assertTrue(order.equals(other));
	}

	@Test
	public void checkEqualityBetweenDifferentObjectsNullOrderCost() {
		order.setOrderCost(null);
		other.setOrderCost(null);
		assertTrue(order.equals(other));
	}

	@Test
	public void nullOrderId() {
		order.setOrderId(null);
		assertFalse(order.equals(other));
	}

	@Test
	public void nullOrderIdOnBoth() {
		order.setOrderId(null);
		other.setOrderId(null);
		assertTrue(order.equals(other));
	}

	@Test
	public void otherOrderIdDifferent() {
		other.setOrderId(2L);
		assertFalse(order.equals(other));
	}

	@Test
	public void otherCustomerIdDifferent() {
		other.setCustomerId(30L);
		assertFalse(order.equals(other));
	}

	@Test
	public void nullCustomerId() {
		order.setCustomerId(null);
		assertFalse(order.equals(other));
	}

	@Test
	public void otherDiscountDifferent() {
		other.setDiscount(2D);
		assertFalse(order.equals(other));
	}

	@Test
	public void constructorWithoutOrderId() {
		Order order = new Order(20D, 12L, 20D);
		assertNull(order.getOrderId());
		assertNotNull(order.getOrderCost());
		assertNotNull(order.getCustomerId());
		assertNotNull(order.getDiscount());

	}

	@Test
	public void hashCodeTest() {
		assertEquals(order.hashCode(), other.hashCode());
	}
}
