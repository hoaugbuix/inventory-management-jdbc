package com.hoangbuix.dev.dao;

import java.util.List;

public interface RoleDAO<E> extends BaseDAO<E> {
    int saveRole(E instance);

    void updateRole(E instance);

    List<E> findAll();

    E findRoleByName(String roleName);

    E findRoleById(int id);
}
