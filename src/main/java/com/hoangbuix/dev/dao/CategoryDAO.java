package com.hoangbuix.dev.dao;

public interface CategoryDAO<E> extends BaseDAO<E>{
    int saveCategory(E instance);
    void UpdateCategory(E instance);
    E findById(int id);
    E findByCode(String code);
    int checkProductInCategory(int id);
}
