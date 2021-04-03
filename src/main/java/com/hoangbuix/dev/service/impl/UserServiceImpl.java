package com.hoangbuix.dev.service.impl;

import com.hoangbuix.dev.dao.RoleDAO;
import com.hoangbuix.dev.dao.UserDAO;
import com.hoangbuix.dev.dao.UserRoleDAO;
import com.hoangbuix.dev.entity.RoleEntity;
import com.hoangbuix.dev.entity.UserEntity;
import com.hoangbuix.dev.entity.UserRoleEntity;
import com.hoangbuix.dev.exception.DuplicateRecordException;
import com.hoangbuix.dev.model.mapper.UserMapper;
import com.hoangbuix.dev.model.request.CreateUserReq;
import com.hoangbuix.dev.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Collections;
import java.util.Date;
import java.util.List;


@Component
public class UserServiceImpl implements UserService {
    @Autowired
    private UserDAO<UserEntity> userDAO;

    @Autowired
    private UserRoleDAO<UserRoleEntity> userRoleDAO;

    @Autowired
    private RoleDAO<RoleEntity> roleDAO;

    @Override
    public UserEntity findById(int id) {
        return userDAO.findUserById(id);
    }

    @Override
    public UserEntity findUserByUsername(String username) {
        return userDAO.findUserByUsername(username);
    }

    @Override
    public List<UserEntity> getAllUser() {
        return userDAO.findAllUser();
    }

    @Override
    public UserEntity saveUser(UserEntity user) {
        // Check email exist
//        UserEntity users = userDAO.findUserByEmailAndUsername(user.getEmail(), user.getUsername());
//        if (users != null) {
//            throw new DuplicateRecordException("Email đã tồn tại trong hệ thống. Vui lòng sử dụng email khác.");
//        }
        user.setActiveFlag(1);
        user.setCreatedDate(new Date());
        user.setUpdatedDate(new Date());
        int id = userDAO.saveUser(user);
        RoleEntity role = roleDAO.findRoleByName("user");
        UserRoleEntity userRole = new UserRoleEntity();
        userRole.setActiveFlag(1);
        userRole.setCreatedDate(new Date());
        userRole.setUpdatedDate(new Date());
        userRole.setUsers(user);
        userRole.setRoles(role);
        userRoleDAO.save(userRole);
        return userDAO.findUserById(id);
    }
}
