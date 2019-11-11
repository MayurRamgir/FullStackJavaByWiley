package com.fullstackdevelopment.myeshop.dao;

import java.util.List;

public interface GenericDAO<T> {
    T get(int id);
    List<T> getAll();
    Long save(T t);
    void update(T t);
    void delete(T t);
}