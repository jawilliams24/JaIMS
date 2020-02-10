package com.qa.jdbc;

import java.sql.SQLException;
import java.util.ArrayList;

import com.qa.dao.MysqlCustomerDao;
import com.qa.domain.Customer;

public class Runner {

	public static void main(String[] args) throws SQLException {
		MysqlCustomerDao dao = new MysqlCustomerDao();
		ArrayList<Customer> customers = dao.readAll();
		for(Customer customer:customers) {
			System.out.println(customer.getId()+" "+customer.getFirstName());
			
		}
		
//		Customer billy = new Customer(10L,"Billy","Mays");
//		dao.create(billy);
		
		
	}

}
