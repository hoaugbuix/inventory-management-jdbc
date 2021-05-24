package com.hoangbuix.dev.dao;

import java.util.List;

public interface CategoryDAO<E> extends BaseDAO<E>{
    int saveCategory(E instance);
    void updateCategory(E instance);
    List<E> findAll();
    E findById(int id);
    E findByCode(String code);
    int checkProductInCategory(int id);
}
