package com.qa.jdbc;

import java.sql.SQLException;

import org.apache.log4j.Logger;

import com.qa.controller.Action;
import com.qa.controller.CrudController;
import com.qa.controller.CustomerController;
import com.qa.controller.ItemController;
import com.qa.controller.OrderController;
import com.qa.dao.MysqlCustomerDao;
import com.qa.dao.MysqlItemDao;
import com.qa.dao.MysqlOrderDao;
import com.qa.domain.Domain;
import com.qa.services.CustomerServices;
import com.qa.services.ItemServices;
import com.qa.services.OrderServices;
import com.qa.utils.Config;
import com.qa.utils.Utilities;

/**
 * This is my IMS class, which provides the "door", so to speak to the IMS. This
 * class allows a user to log in with a username and password of their choosing,
 * and then runs other methods depending on user choice.
 * 
 * @author James Williams
 *
 */

public class Ims {

	public static final Logger LOGGER = Logger.getLogger(Ims.class);

	public void imsSystem() {

		LOGGER.info("What is your username");
		Config.username = Utilities.getInput();
		LOGGER.info("What is your password");
		Config.password = Utilities.getInput();

		boolean looper = false;
		while (!looper) {

			LOGGER.info("Which entity would you like to use?");
			Domain.printDomains();

			Domain domain = Domain.getDomain();
			Action action = null;

			if (domain != Domain.STOP) {
				LOGGER.info("What would you like to do with " + domain.name().toLowerCase() + ":");

				Action.printActions();
				action = Action.getAction();
			}

			switch (domain) {
			case CUSTOMER:
				CustomerController customerController = new CustomerController(
						new CustomerServices(new MysqlCustomerDao()));
				doAction(customerController, action);
				break;
			case ITEM:
				ItemController itemController = new ItemController(new ItemServices(new MysqlItemDao()));
				doAction(itemController, action);
				break;
			case ORDER:
				OrderController orderController = new OrderController(new OrderServices(new MysqlOrderDao()));
				doAction(orderController, action);
				break;
			case STOP:
				looper = true;
				break;
			}
		}

	}

	public void doAction(CrudController<?> crudController, Action action) {
		switch (action) {
		case CREATE:
			crudController.create();
			break;
		case READ:
			crudController.readAll();
			break;
		case UPDATE:
			crudController.update();
			break;
		case DELETE:
			crudController.delete();
			break;
		case RETURN:
			break;
		}
	}
}