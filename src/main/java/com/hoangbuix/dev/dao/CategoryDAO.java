package com.hoangbuix.dev.dao;

public interface CategoryDAO<E> extends BaseDAO<E>{
    int save(E instance);
    E findById(int id);
}
