package com.qa.dao;

import java.util.ArrayList;

/**
 * This class is my Database Access Object
 * @author James Williams
 *
 * @param <T>
 */

public interface Dao<T> {
	
	public void create(T t);
	
	public ArrayList<T> getAll();
	
	public void update(T t);
	
	public void delete(int id);
	
}
