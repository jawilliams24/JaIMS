package com.qa.controller;

import java.util.List;

import org.apache.log4j.Logger;

import com.qa.domain.Item;
import com.qa.services.CrudServices;
import com.qa.utils.Utilities;

/**
 * This class interacts with the user to give them instructions and then feeds
 * back to them once they have completed their actions.
 * 
 * @author James Williams
 *
 */

public class ItemController implements CrudController<Item> {

	public static final Logger LOGGER = Logger.getLogger(ItemController.class);

	private CrudServices<Item> itemService;

	public ItemController(CrudServices<Item> itemService) {
		this.itemService = itemService;
	}

	public String getInput() {
		return Utilities.getInput();
	}

	public List<Item> readAll() {
		List<Item> items = itemService.readAll();
		for (Item item : items) {
			LOGGER.info(item.toString());
		}
		return items;
	}

	public Item create() {
		LOGGER.info("Please enter a item name");
		String itemName = getInput();
		LOGGER.info("Please enter a item value");
		Float itemValue = Float.parseFloat(getInput());
		Item item = itemService.create(new Item(itemName, itemValue));
		LOGGER.info("Item successfully created.\n");
		return item;
	}

	public Item update() {
		LOGGER.info("Please enter the id of the item you would like to update");
		Long itemId = Long.valueOf(getInput());
		LOGGER.info("Please enter a item name");
		String itemName = getInput();
		LOGGER.info("Please enter a item value");
		Float itemValue = Float.parseFloat(getInput());
		Item item = itemService.update(new Item(itemId, itemName, itemValue));
		LOGGER.info("Item successfully updated.\n");
		return item;
	}

	public void delete() {
		LOGGER.info("Please enter the id of the item you would like to delete");
		Long itemId = Long.valueOf(getInput());
		itemService.delete(itemId);
		LOGGER.info("Item successfully deleted.\n");

	}

	@Override
	public Item readSingle(long item) {
		return itemService.readSingle(item);
	}

}