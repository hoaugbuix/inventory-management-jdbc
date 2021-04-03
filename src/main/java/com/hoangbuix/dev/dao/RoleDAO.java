package com.hoangbuix.dev.dao;

public interface RoleDAO<E> extends BaseDAO<E>{
    E findRoleByName(String roleName);
}
