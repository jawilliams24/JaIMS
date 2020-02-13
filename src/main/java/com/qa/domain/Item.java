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
	private Float itemValue;

	/**
	 * Constructor for creating new items.
	 * 
	 * @param itemName
	 * @param itemValue
	 * @param itemQuantity
	 */

	public Item(String itemName, Float itemValue) {
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

	public Item(Long itemId, String itemName, Float itemValue) {
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

	public Item(Long itemId, String itemName, Float itemValue, Long itemQuantity) {
		this.itemId = itemId;
		this.itemName = itemName;
		this.itemValue = itemValue;
		this.itemQuantity = itemQuantity;
	}
	
	public Item(String itemName, Float itemValue, Long itemQuantity) {
		// TODO Auto-generated constructor stub
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

	public Float getItemValue() {
		return itemValue;
	}

	public void setItemValue(Float itemValue) {
		this.itemValue = itemValue;
	}

	public String toString() {
		return "id:" + itemId + " item name:" + itemName + " quantity:" + itemQuantity + " item value:" + itemValue;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((itemId == null) ? 0 : itemId.hashCode());
		result = prime * result + ((itemName == null) ? 0 : itemName.hashCode());
		result = prime * result + ((itemQuantity == null) ? 0 : itemQuantity.hashCode());
		result = prime * result + ((itemValue == null) ? 0 : itemValue.hashCode());
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
		Item other = (Item) obj;
		if (itemId == null) {
			if (other.itemId != null)
				return false;
		} else if (!itemId.equals(other.itemId))
			return false;
		if (itemName == null) {
			if (other.itemName != null)
				return false;
		} else if (!itemName.equals(other.itemName))
			return false;
		if (itemQuantity == null) {
			if (other.itemQuantity != null)
				return false;
		} else if (!itemQuantity.equals(other.itemQuantity))
			return false;
		if (itemValue == null) {
			if (other.itemValue != null)
				return false;
		} else if (!itemValue.equals(other.itemValue))
			return false;
		return true;
	}

	

}
