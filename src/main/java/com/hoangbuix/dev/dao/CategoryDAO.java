package com.hoangbuix.dev.dao;

import java.util.List;

public interface CategoryDAO<E> extends BaseDAO<E>{
    int save(E instance);
    E findById(int id);
    List<E> findByCode(String code);
}
