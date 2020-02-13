package com.qa.jdbc.domain;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertTrue;

import org.junit.Before;
import org.junit.Test;

import com.qa.domain.Item;

public class ItemTest {

	private Item item;
	private Item other;

	@Before
	public void setUp() {
		item = new Item(1L, "apple", 20F, 12L);
		other = new Item(1L, "apple", 20F, 12L);
	}

	@Test
	public void settersTest() {
		assertNotNull(item.getItemId());
		assertNotNull(item.getItemName());
		assertNotNull(item.getItemValue());
		assertNotNull(item.getItemQuantity());

		item.setItemId(null);
		assertNull(item.getItemId());
		item.setItemName(null);
		assertNull(item.getItemName());
		item.setItemValue(null);
		assertNull(item.getItemValue());
		item.setItemQuantity(null);
		assertNull(item.getItemQuantity());

	}

	@Test
	public void equalsWithNull() {
		assertFalse(item.equals(null));
	}

	@Test
	public void equalsWithDifferentObject() {
		assertFalse(item.equals(new Object()));
	}

	@Test
	public void createItemWithId() {
		assertEquals(1L, item.getItemId(), 0);
		assertEquals("apple", item.getItemName());
		assertEquals(20F, item.getItemValue(), 0F);
		assertEquals(12L, item.getItemQuantity(), 0L);
	}

	@Test
	public void checkEquality() {
		assertTrue(item.equals(item));
	}

	@Test
	public void checkEqualityBetweenDifferentObjects() {
		assertTrue(item.equals(other));
	}

	@Test
	public void itemNameNullButOtherNameNotNull() {
		item.setItemName(null);
		assertFalse(item.equals(other));
	}

	@Test
	public void itemNamesNotEqual() {
		other.setItemName("banana");
		assertFalse(item.equals(other));
	}

	@Test
	public void checkEqualityBetweenDifferentObjectsNullName() {
		item.setItemName(null);
		other.setItemName(null);
		assertTrue(item.equals(other));
	}

	@Test
	public void nullItemId() {
		item.setItemId(null);
		assertFalse(item.equals(other));
	}

	@Test
	public void nullItemIdOnBoth() {
		item.setItemId(1L);
		other.setItemId(1L);
		assertFalse(item.equals(other));
	}

	@Test
	public void otherItemIdDifferent() {
		other.setItemId(2L);
		assertFalse(item.equals(other));
	}

	@Test
	public void otherValueDifferent() {
		other.setItemValue(30F);
		assertFalse(item.equals(other));
	}

	@Test
	public void nullQuantity() {
		item.setItemQuantity(null);
		assertFalse(item.equals(other));
	}

	@Test
	public void otherQuantityDifferent() {
		other.setItemQuantity(2L);
		assertFalse(item.equals(other));
	}

	@Test
	public void constructorWithoutItemId() {
		Item item = new Item(null, "apple", 20F, 12L);
		assertNull(item.getItemId());
		assertNotNull(item.getItemName());
		assertNotNull(item.getItemValue());
		assertNotNull(item.getItemQuantity());
	}

	@Test
	public void hashCodeTest() {
		assertEquals(item.hashCode(), other.hashCode());
	}

	@Test
	public void toStringTest() {
		String toString = "item_id:1 item_name:Apple item_value:20 item_quantity:12";
		assertEquals(toString, item.toString());
	}
}
