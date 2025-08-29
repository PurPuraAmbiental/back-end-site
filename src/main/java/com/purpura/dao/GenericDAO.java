package com.purpura.dao;

import java.util.List;

public interface GenericDAO<T> {
    boolean save(T entidade);
    void update(T entidade);
    void deleteById(int id);
    T findById(int id);
    List<T> findAll();
}
