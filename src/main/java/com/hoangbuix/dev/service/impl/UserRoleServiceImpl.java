package com.hoangbuix.dev.service.impl;

import com.hoangbuix.dev.dao.RoleDAO;
import com.hoangbuix.dev.dao.UserDAO;
import com.hoangbuix.dev.dao.UserRoleDAO;
import com.hoangbuix.dev.entity.RoleEntity;
import com.hoangbuix.dev.entity.UserEntity;
import com.hoangbuix.dev.entity.UserRoleEntity;
import com.hoangbuix.dev.model.request.create.CreateUserRoleReq;
import com.hoangbuix.dev.service.UserRoleService;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class UserRoleServiceImpl implements UserRoleService {
    final Logger log = Logger.getLogger(UserRoleServiceImpl.class);
    @Autowired
    private UserRoleDAO<UserRoleEntity> userRoleDAO;
    @Autowired
    UserDAO<UserEntity> userDAO;
    @Autowired
    RoleDAO<RoleEntity> roleDAO;

    @Override
    public UserRoleEntity save(CreateUserRoleReq req) {
        int id = 0;
        try {
            log.info("save user_role = "+ req);
            if (req != null){
                UserRoleEntity userRole = new UserRoleEntity();
                UserEntity user = userDAO.findUserById(req.getUserId());
                RoleEntity role = roleDAO.findRoleById(req.getRoleId());
                userRole.setUsers(user);
                userRole.setRoles(role);
                id = userRoleDAO.save(userRole);
            }
        }catch (Exception e) {
            e.printStackTrace();
            log.info(e.getMessage());
        }
        return userRoleDAO.findById(id);
    }

    @Override
    public List<UserRoleEntity> findAll() {
        return userRoleDAO.findAll();
    }
}
