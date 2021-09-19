package com.hoangbuix.dev.dao.impl;

import com.hoangbuix.dev.dao.UserRoleDAO;
import com.hoangbuix.dev.entity.UserRoleEntity;
import com.hoangbuix.dev.model.mapper.UserRoleMapper;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Repository
@Transactional(rollbackFor = Exception.class)
public class UserRoleDAOImpl extends BaseDAOImpl<UserRoleEntity> implements UserRoleDAO<UserRoleEntity> {

    @Override
    public int save(UserRoleEntity userRole) {
        String sql ="call userRole_create(?, ?)";
        return insert(sql, userRole.getUsers().getId(), userRole.getRoles().getId());
    }

    @Override
    public List<UserRoleEntity> findAll() {
        String sql = "call userRole_findAll()";
        return query(sql, new UserRoleMapper());
    }

    @Override
    public UserRoleEntity findById(int id) {
        String sql = "call userRole_findById(?)";
        List<UserRoleEntity> userRole = query(sql, new UserRoleMapper(), id);
        return userRole.isEmpty() ? null : userRole.get(0);
    }
}
