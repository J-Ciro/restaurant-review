package org.example.repositories;

import java.util.List;

public interface IRepository<T>{

    void save(T entity);
    T findByName(String name);
    void delete(String name);
    List<T> findAll();


}
