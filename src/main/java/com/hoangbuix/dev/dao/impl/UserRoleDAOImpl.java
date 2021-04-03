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
        StringBuilder sql = new StringBuilder();
        sql.append("INSERT INTO user_role");
//        sql.append("(user_id, role_id, active_flag, created_date, updated_date)");
        sql.append(" VALUES (?, ?, ?, ?, ?)");
        return insert(sql.toString(), userRole.getUsers(),
                userRole.getRoles(), userRole.getActiveFlag(),
                userRole.getCreatedDate(), userRole.getUpdatedDate());
    }

    @Override
    public List<UserRoleEntity> findAll() {
        String sql ="select * from user_role";
        return query(sql, new UserRoleMapper());
    }
}
