package com.qa.services;

import java.util.List;

/**
 * This interface allows the individual function service classes to implement
 * its methods.
 * @author James williams
 * 
 * @param <T>
 */

public interface CrudServices<T> {

    public List<T> readAll();
     
    T create(T t);
     
    T update(T t);
 
    void delete(Long id);

}
