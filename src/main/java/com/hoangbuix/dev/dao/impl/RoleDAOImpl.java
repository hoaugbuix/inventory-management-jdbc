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
    public int saveRole(RoleEntity role) {
        StringBuilder sql = new StringBuilder();
        sql.append("call role_create(?, ?, ?, ?, ?)");
        return insert(sql.toString(), role.getRoleName(), role.getDescription(),
                role.getActiveFlag(), role.getCreatedDate(), role.getUpdatedDate());
    }

    @Override
    public void updateRole(RoleEntity role) {
        StringBuilder sql = new StringBuilder();
        sql.append("call role_update(?, ?, ?, ?)");
        update(sql.toString(), role.getRoleName(), role.getDescription(), role.getActiveFlag(), role.getUpdatedDate());
    }

    @Override
    public List<RoleEntity> findAll() {
        String sql = "call role_findAll()";
        return query(sql, new RoleMapper());
    }

    @Override
    public RoleEntity findRoleByName(String roleName) {
        String sql = "call findRoleByRoleName(?)";
        List<RoleEntity> role = query(sql, new RoleMapper(), roleName);
        return role.isEmpty() ? null : role.get(0);
    }

    @Override
    public RoleEntity findRoleById(int id) {
        String sql = "call findRoleById(?)";
        List<RoleEntity> role = query(sql, new RoleMapper(), id);
        return role.isEmpty() ? null : role.get(0);
    }
}
