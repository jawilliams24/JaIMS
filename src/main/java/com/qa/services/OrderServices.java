package com.qa.services;

import java.util.List;

import com.qa.dao.Dao;
import com.qa.domain.Order;
/**
 * This class runs the methods listed below to allow the user
 * to interact with the database's items table.
 * @author James Williams
 *
 */


public class OrderServices implements CrudServices<Order> {
	Dao<Order> orderDao;


	public OrderServices(Dao<Order> orderDao) {
		this.orderDao = orderDao;
	}


	@Override
	public List<Order> readAll() {
		return orderDao.readAll();
	}

	@Override
	public Order create(Order order) {
		return orderDao.create(order);
	}

	@Override
	public Order update(Order order) {
		return orderDao.update(order);

	}

	@Override
	public void delete(Long id) {
		orderDao.delete(id);

	}

	@Override
	public Order readSingle(long order) {
		return orderDao.readSingle(order);
	}

}