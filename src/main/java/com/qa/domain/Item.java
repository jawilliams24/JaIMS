package com.qa.domain;

/**
 * This class is to create Items for my database
 * 
 * @author James Williams
 *
 */

public class Item {

	private Long itemId;
	private String itemName;
	private Long itemQuantity;
	private float itemValue;

	/**
	 * Constructor for creating new items.
	 * 
	 * @param itemName
	 * @param itemValue
	 * @param itemQuantity
	 */

	public Item(String itemName, float itemValue) {
		this.itemName = itemName;
		this.itemValue = itemValue;
	}

	/**
	 * Constructor for reading items.
	 * 
	 * @param itemId
	 * @param itemName
	 * @param itemValue
	 * @param itemQuantity
	 */

	public Item(Long itemId, String itemName, float itemValue) {
		this.itemId = itemId;
		this.itemName = itemName;
		this.itemValue = itemValue;
	}

	public Item(Long itemId) {
		this.itemId = itemId;
	}

	public Item(Long itemId, Long itemQuantity) {
		this.itemId = itemId;
		this.itemQuantity = itemQuantity;
	}

	/**
	 * Getters and Setters for Item parameters.
	 * 
	 */

	public Long getItemId() {
		return itemId;
	}

	public void setItemId(Long itemId) {
		this.itemId = itemId;
	}

	public String getItemName() {
		return itemName;
	}

	public void setItemName(String itemName) {
		this.itemName = itemName;
	}

	public Long getItemQuantity() {
		return itemQuantity;
	}

	public void setItemQuantity(Long itemQuantity) {
		this.itemQuantity = itemQuantity;
	}

	public float getItemValue() {
		return itemValue;
	}

	public void setItemValue(float itemValue) {
		this.itemValue = itemValue;
	}

	public String toString() {
		return "id:" + itemId + " item name:" + itemName + " quantity:" + itemQuantity + " item value:" + itemValue;
	}

}
