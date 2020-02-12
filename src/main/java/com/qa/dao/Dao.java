package com.qa.dao;

import java.util.List;

/**
 * This class is my Database Access Object
 * @author James Williams
 *
 * @param <T>
 */

public interface Dao<T> {
	
	public T create(T t);
	
	public List<T> readAll();
	
	public T readSingle(T t);
	
	public T update(T t);
	
	public void delete(long id);
	
}
