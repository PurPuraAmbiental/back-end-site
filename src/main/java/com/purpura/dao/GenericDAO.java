package com.purpura.dao;

import java.util.List;

public interface GenericDAO<T> {
    boolean save(T entidade);
    boolean update(T entidade);
    boolean delete(int id);
    T find(int id);
    List<T> findAll();
}
