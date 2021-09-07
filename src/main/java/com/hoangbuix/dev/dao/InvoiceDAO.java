package com.hoangbuix.dev.dao;

import java.util.List;

public interface InvoiceDAO<E> extends BaseDAO<E> {
    int save(E instance);

    void update(E instance);

    E findById(int id);

    List<E> findAll(int type);
}
