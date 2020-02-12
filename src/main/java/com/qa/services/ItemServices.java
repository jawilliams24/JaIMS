package com.qa.services;

import java.util.List;

import com.qa.dao.Dao;
import com.qa.domain.Item;

/**
 * This class runs the methods listed below to allow the user
 * to interact with the database's items table.
 * @author James Williams
 *
 */

public class ItemServices implements CrudServices<Item> {

	Dao<Item> itemDao;
	
	public ItemServices(Dao<Item> itemDao) {
		this.itemDao = itemDao;
	}
	
	public List<Item> readAll() {
		return itemDao.readAll();
	}

	public Item create(Item item) {
		return itemDao.create(item);
	}

	public Item update(Item item) {
		return itemDao.update(item);
	}

	public void delete(Long id) {
		itemDao.delete(id);
	}

}