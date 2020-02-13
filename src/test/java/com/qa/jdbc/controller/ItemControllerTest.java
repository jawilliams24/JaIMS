package com.qa.jdbc.controller;

import static org.junit.Assert.assertEquals;

import java.util.List;
import java.util.ArrayList;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito; 
import org.mockito.Spy;
import org.mockito.junit.MockitoJUnitRunner;

import com.qa.controller.ItemController;
import com.qa.domain.Customer;
import com.qa.domain.Item;
import com.qa.services.CustomerServices;
import com.qa.services.ItemServices;

@RunWith(MockitoJUnitRunner.class)
public class ItemControllerTest {

	/**
	 * The thing I want to fake functionality for
	 */
	@Mock
	private ItemServices itemServices;

	/**
	 * Spy is used because I want to mock some methods inside the item I'm testing
	 * InjectMocks uses dependency injection to insert the mock into the customer
	 * controller
	 */
	@Spy
	@InjectMocks
	private ItemController itemController;
	
	@Test
	public void createTest() {
		String itemName = "Chris";
		String itemValue = "40";
		Mockito.doReturn(itemName, itemValue).when(itemController).getInput();
		Item item = new Item(itemName, 40F);
		Item savedItem = new Item(1L, "Chris", 40F);
		savedItem.setItemQuantity(6L);
		Mockito.when(itemServices.create(item)).thenReturn(savedItem);
		assertEquals(savedItem, itemController.create());
	}

	@Test
	public void readAllTest() {
		ItemController itemController = new ItemController(itemServices);
		Item item1 = (new Item("Apple", 40F));
		Item item2 = (new Item("Orange", 30F));
		Item item3 = (new Item("Banana", 20F));
		List<Item> items = new ArrayList<>();
		items.add(item1);
		items.add(item2);
		items.add(item3);
		Mockito.when(itemServices.readAll()).thenReturn(items);
		assertEquals(items, itemController.readAll());
	}

	

	/**
	 * Delete doesn't return anything, so we can just verify that it calls the
	 * delete method
	 */
	@Test
	public void deleteTest() {
		String itemId = "1";
		Mockito.doReturn(itemId).when(itemController).getInput();
		itemController.delete();
		Mockito.verify(itemServices, Mockito.times(1)).delete(1L);
	}

}
