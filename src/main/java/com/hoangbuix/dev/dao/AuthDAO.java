package com.hoangbuix.dev.dao;

public interface AuthDAO<E> extends BaseDAO<E>{
    void save(E instance);
    void update(E instance);
    E findByRoleIdAndMenuId(int roleId, int menuId);
}
