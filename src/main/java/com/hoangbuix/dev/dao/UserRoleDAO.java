package com.hoangbuix.dev.dao;

import java.util.List;

public interface UserRoleDAO<E> extends BaseDAO<E> {
    int save(E instance);

    List<E> findAll();
}
