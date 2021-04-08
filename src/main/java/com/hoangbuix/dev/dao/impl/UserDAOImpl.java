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
        sql.append("INSERT INTO user");
        sql.append("(first_name, last_name, avatar, user_name, password, email, active_flag, created_date, updated_date)");
        sql.append("VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?)");
        return insert(sql.toString(), user.getFirstName(), user.getLastName(),
                user.getAvatar(), user.getUsername(), user.getPassword(),
                user.getEmail(), user.getActiveFlag(),
                user.getCreatedDate(), user.getUpdatedDate());
    }

    @Override
    public void updateUser(UserEntity user) {
        StringBuilder sql = new StringBuilder("UPDATE user SET");
        sql.append("first_name = ?, last_name = ?, avatar = ?,");
        sql.append(" user_name = ?, password = ?, email = ?, active_flag = ?, created_date = ?, updated_date = ?");
        update(sql.toString(), user.getFirstName(), user.getLastName(),
                user.getAvatar(), user.getUsername(), user.getPassword(),
                user.getEmail(), user.getActiveFlag(), user.getCreatedDate(), user.getUpdatedDate());
    }

    @Override
    public void deleteUser(int id) {
        String sql = "UPDATE user SET active_flag = 0, updated_date = now() WHERE id = ?";
        update(sql,id);
    }

    @Override
    public List<UserEntity> findAllUser() {
        String sql = "SELECT * FROM user where active_flag = 1";
        return query(sql, new UserMapper());
    }

    @Override
    public UserEntity findUserById(int id) {
        String sql = "SELECT * FROM user WHERE id = ?";
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
}
