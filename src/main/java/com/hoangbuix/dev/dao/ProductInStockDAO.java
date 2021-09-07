package com.hoangbuix.dev.dao;

import java.util.List;

public interface ProductInStockDAO<E> extends BaseDAO<E> {
    int save(E instance);

    void update(E instance);

    List<E> findAll();

    E findById(int id);

    E findByCode(String code);

    E exist();
}
