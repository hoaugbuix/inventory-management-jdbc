package com.hoangbuix.dev.dao.impl;

import com.hoangbuix.dev.dao.AuthDAO;
import com.hoangbuix.dev.entity.AuthEntity;
import com.hoangbuix.dev.model.mapper.AuthMapper;
import com.hoangbuix.dev.model.mapper.HistoryMapper;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Repository
@Transactional(rollbackFor = Exception.class)
public class AuthDAOImpl extends BaseDAOImpl<AuthEntity> implements AuthDAO<AuthEntity> {
    @Override
    public void save(AuthEntity auth) {
        String sql = "call auth_create(?,?,?,?,?,?)";
        insert(sql,auth.getRoles(), auth.getMenus(), auth.getPermission(),
                auth.getActiveFlag(), auth.getCreatedDate(), auth.getUpdatedDate());
    }

    @Override
    public void update(AuthEntity auth) {
        String sql = "call auth_update(?,?,?,?,?)";
        update(sql, auth.getRoles(), auth.getMenus(), auth. getPermission(),
                auth.getActiveFlag(), auth.getUpdatedDate());
    }

    @Override
    public AuthEntity findByRoleIdAndMenuId(int roleId, int menuId) {
        String sql = "call menu_findByRoleIdAndMenuId(?,?) ";
        List<AuthEntity> auth = query(sql, new AuthMapper(), roleId, menuId);
        return auth.isEmpty() ? null : auth.get(0);
    }

    @Override
    public List<AuthEntity> findAll() {
        String sql = "call auth_findAll()";
        return query(sql, new AuthMapper());
    }
}
