package com.hoangbuix.dev.dao.impl;

import com.hoangbuix.dev.dao.UserDAO;
import com.hoangbuix.dev.entity.UserEntity;
import com.hoangbuix.dev.model.mapper.UserMapper;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Repository
@Transactional(rollbackFor = Exception.class)
public class UserDAOImpl extends BaseDAOImpl<UserEntity> implements UserDAO<UserEntity> {
    @Override
    public int saveUser(UserEntity user) {
        StringBuilder sql = new StringBuilder();
        sql.append("call user_create(?, ?, ?, ?, ?, ?, ?, ?, ?)");
        return insert(sql.toString(), user.getFirstName(), user.getLastName(),
                user.getAvatar(), user.getUsername(), user.getPassword(),
                user.getEmail(), user.getActiveFlag(),
                user.getCreatedDate(), user.getUpdatedDate());
    }

    @Override
    public void updateUser(UserEntity user) {
        StringBuilder sql = new StringBuilder();
        sql.append("call user_update(?, ?, ?, ?, ?, ?, ?, ?)");
        update(sql.toString(), user.getFirstName(), user.getLastName(),
                user.getAvatar(), user.getUsername(), user.getPassword(),
                user.getEmail(), user.getActiveFlag(), user.getUpdatedDate());
    }

    @Override
    public void deleteUser(int id) {
        String sql = "UPDATE user SET active_flag = 0, updated_date = now() WHERE id = ?";
        update(sql,id);
    }

    @Override
    public List<UserEntity> findAllUser() {
        String sql = "call user_getAll()";
        return query(sql, new UserMapper());
    }

    @Override
    public UserEntity findUserById(int id) {
        String sql = "call user_findUserById(?)";
        List<UserEntity> user = query(sql, new UserMapper(), id);
        return user.isEmpty() ? null : user.get(0);
    }

    @Override
    public UserEntity findUserByUsername(String username) {
        String sql = "select * from user where user_name = ?";
        List<UserEntity> user = query(sql,new UserMapper(), username);
        return user.isEmpty() ? null : user.get(0);
    }

    @Override
    public UserEntity findUserByEmailAndUsername(String email, String username) {
        String sql = "select * from user where email = ? and user_name = ?";
        List<UserEntity> user = query(sql,new UserMapper(), email, username);
        return user.isEmpty() ? null : user.get(0);
    }

    @Override
    public UserEntity findByUserAndRole() {
        return null;
    }
}
