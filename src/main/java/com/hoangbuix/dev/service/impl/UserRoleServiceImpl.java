package com.hoangbuix.dev.service.impl;

import com.hoangbuix.dev.dao.UserRoleDAO;
import com.hoangbuix.dev.entity.UserRoleEntity;
import com.hoangbuix.dev.service.UserRoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class UserRoleServiceImpl implements UserRoleService {
    @Autowired
    private UserRoleDAO<UserRoleEntity> userRoleDAO;

    @Override
    public List<UserRoleEntity> findAll() {
        return userRoleDAO.findAll();
    }
}
