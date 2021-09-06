package com.hoangbuix.dev.service.impl;

import com.hoangbuix.dev.dao.RoleDAO;
import com.hoangbuix.dev.dao.UserDAO;
import com.hoangbuix.dev.dao.UserRoleDAO;
import com.hoangbuix.dev.entity.RoleEntity;
import com.hoangbuix.dev.entity.UserEntity;
import com.hoangbuix.dev.entity.UserRoleEntity;
import com.hoangbuix.dev.exception.DuplicateRecordException;
import com.hoangbuix.dev.exception.InternalServerException;
import com.hoangbuix.dev.exception.NotFoundException;
import com.hoangbuix.dev.model.converter.UserConverter;
import com.hoangbuix.dev.model.dto.UserDTO;
import com.hoangbuix.dev.model.request.update.UpdateUserReq;
import com.hoangbuix.dev.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.sql.Timestamp;
import java.util.List;


@Component
public class UserServiceImpl implements UserService {
    @Autowired
    private UserDAO<UserEntity> userDAO;

    @Autowired
    private UserRoleDAO<UserRoleEntity> userRoleDAO;

    @Autowired
    private RoleDAO<RoleEntity> roleDAO;

    @Autowired
    private UserConverter userConverter;

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
    public UserEntity saveUser(UserDTO req) {
        // Check email exist
        UserEntity user = userDAO.findUserByUsername(req.getUsername());
        if (user != null) {
            throw new DuplicateRecordException("Email đã tồn tại trong hệ thống. Vui lòng sử dụng email khác.");
        }
        user = userConverter.toEntity(req);
        int id = userDAO.saveUser(user);
        RoleEntity role = roleDAO.findRoleByName("user");
        if (id != 0){
            UserRoleEntity userRole = new UserRoleEntity();
            userRole.setActiveFlag(1);
            userRole.setCreatedDate(new Timestamp(System.currentTimeMillis()));
            userRole.setUpdatedDate(new Timestamp(System.currentTimeMillis()));
            userRole.setUserId(id);
            userRole.setRoleId(role.getId());
            userRoleDAO.save(userRole);
        }
        return userDAO.findUserById(id);
    }

    @Override
    public UserEntity updateUser(int id, UpdateUserReq req) {
        UserEntity user = userDAO.findUserById(id);
        if(user == null) {
            throw new NotFoundException("");
        }
        try {
            userDAO.updateUser(user);
        }catch (Exception e){
            throw new InternalServerException("xxx");
        }
        return null;
    }

    @Override
    public void deleteUser(int id) {
        userDAO.deleteUser(id);
    }

}
