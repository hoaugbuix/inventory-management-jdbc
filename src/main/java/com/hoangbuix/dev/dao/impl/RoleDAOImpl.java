package com.hoangbuix.dev.dao.impl;

import com.hoangbuix.dev.dao.RoleDAO;
import com.hoangbuix.dev.entity.RoleEntity;
import com.hoangbuix.dev.model.mapper.RoleMapper;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Repository
@Transactional(rollbackFor = Exception.class)
public class RoleDAOImpl extends BaseDAOImpl<RoleEntity> implements RoleDAO<RoleEntity> {
    @Override
    public RoleEntity findRoleByName(String roleName) {
        String sql = "SELECT * FROM role WHERE active_flag = 1 AND role_name = ?";
        List<RoleEntity> role = query(sql, new RoleMapper(), roleName);
        return role.isEmpty() ? null : role.get(0);
    }
}
