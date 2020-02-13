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
	
	@Override
	public List<Item> readAll() {
		return itemDao.readAll();
	}

	@Override
	public Item create(Item item) {
		return itemDao.create(item);
	}

	@Override
	public Item update(Item item) {
		return itemDao.update(item);
	}

	@Override
	public void delete(Long id) {
		itemDao.delete(id);
	}

	@Override
	public Item readSingle(Item item) {
		return itemDao.readSingle(item);
	}

}