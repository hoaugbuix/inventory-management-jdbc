package com.hoangbuix.dev.dao.impl;

import com.hoangbuix.dev.dao.AuthDAO;
import com.hoangbuix.dev.entity.AuthEntity;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

@Repository
@Transactional(rollbackFor = Exception.class)
public class AuthDAOImpl extends BaseDAOImpl<AuthEntity> implements AuthDAO<AuthEntity> {
    @Override
    public void save(AuthEntity instance) {
    }

    @Override
    public void update(AuthEntity instance) {

    }

    @Override
    public AuthEntity findByRoleIdAndMenuId(int roleId, int menuId) {
        return null;
    }
}
