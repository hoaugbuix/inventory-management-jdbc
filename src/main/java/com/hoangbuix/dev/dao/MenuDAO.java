package com.hoangbuix.dev.dao;

import java.util.List;

public interface MenuDAO<E> extends BaseDAO<E> {
    List<E> findAll();
    E findById(int id);
    int save(E instance);
    void update(E instance);
    void changeStatus(int id);
    void updatePermission(int roleId,int menuId,int permission);
}
