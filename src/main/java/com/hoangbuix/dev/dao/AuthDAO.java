package com.hoangbuix.dev.dao;

import java.util.List;

public interface AuthDAO<E> extends BaseDAO<E> {
    void save(E instance);

    void update(E instance);

    E findByRoleIdAndMenuId(int roleId, int menuId);

    List<E> findAll();
}
