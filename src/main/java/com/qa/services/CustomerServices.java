package com.qa.services;

import java.util.List;

import com.qa.dao.Dao;
import com.qa.domain.Customer;

/**
 * This class runs the methods listed below to allow the user
 * to interact with the database's customers table.
 * @author James Williams
 *
 */

public class CustomerServices implements CrudServices<Customer> {

	Dao<Customer> customerDao;
	
	public CustomerServices(Dao<Customer> customerDao) {
		this.customerDao = customerDao;
	}
	
	public List<Customer> readAll() {
		return customerDao.readAll();
	}

	public Customer create(Customer customer) {
		return customerDao.create(customer);
	}

	public Customer update(Customer customer) {
		return customerDao.update(customer);
	}

	public void delete(Long id) {
		customerDao.delete(id);
	}

}