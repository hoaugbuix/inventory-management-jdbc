package com.hoangbuix.dev.dao;

import java.util.List;

public interface UserDAO<E> extends BaseDAO<E> {
    int saveUser(E instance);
    void updateUser(E instance);
    void deleteUser(int id);
    List<E> findAllUser();
    E findUserById(int id);
    E findUserByUsername(String username);
    E findUserByEmailAndUsername(String email, String username);
    E getUserAndRole();

    E findAll();
    int save(E instance);
}
