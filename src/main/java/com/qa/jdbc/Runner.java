package com.qa.jdbc;

import java.sql.SQLException;
import java.util.ArrayList;

public class Runner {

	public static void main(String[] args) throws SQLException {
		MysqlCustomerDao dao = new MysqlCustomerDao();
		ArrayList<Customer> customers = dao.getAll();
		for(Customer customer:customers) {
			System.out.println(customer.getId()+" "+customer.getFirstName());
			
		}
	}

}
