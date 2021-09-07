package com.hoangbuix.dev.dao;

import java.util.List;

public interface HistoryDAO<E> extends BaseDAO<E> {
    int save(E instance);

    List<E> findAll();

    E findById(int id);
}
