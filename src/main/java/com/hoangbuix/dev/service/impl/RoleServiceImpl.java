package com.hoangbuix.dev.service.impl;

import com.hoangbuix.dev.dao.RoleDAO;
import com.hoangbuix.dev.entity.RoleEntity;
import com.hoangbuix.dev.exception.DuplicateRecordException;
import com.hoangbuix.dev.exception.InternalServerException;
import com.hoangbuix.dev.exception.NotFoundException;
import com.hoangbuix.dev.model.request.CreateRoleReq;
import com.hoangbuix.dev.model.request.UpdateRoleReq;
import com.hoangbuix.dev.service.RoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.sql.Timestamp;
import java.util.List;

@Component
public class RoleServiceImpl implements RoleService {
    @Autowired
    private RoleDAO<RoleEntity> roleDAO;

    @Override
    public RoleEntity saveRole(CreateRoleReq req) {
        RoleEntity role = new RoleEntity();
        Object obj = roleDAO.findRoleByName(req.getRoleName());
        if (obj == null){
            role.setRoleName(req.getRoleName());
            role.setDescription(req.getDescription());
            role.setActiveFlag(1);
            role.setCreatedDate(new Timestamp(System.currentTimeMillis()));
            role.setUpdatedDate(new Timestamp(System.currentTimeMillis()));
        } else {
            throw new DuplicateRecordException("dup roi nha");
        }
        int id = roleDAO.saveRole(role);
        return roleDAO.findRoleById(id);
    }

    @Override
    public void updateRole(int id, UpdateRoleReq req) {
        RoleEntity role = roleDAO.findRoleById(id);
        if (role == null){
            throw new NotFoundException("Role không tồn tại");
        }
        try {
            role.setRoleName(req.getRoleName());
            role.setDescription(req.getDescription());
            role.setActiveFlag(req.getActiveFlag());
            role.setUpdatedDate(new Timestamp(System.currentTimeMillis()));
            roleDAO.updateRole(role);
        }catch (Exception e){
            throw new InternalServerException("Lỗi khi chỉnh sửa role");
        }
    }

    @Override
    public void deleteRole(int id) {
        RoleEntity role = roleDAO.findRoleById(id);
        if (role == null){
            throw new NotFoundException("Role không tồn tại");
        }
        try {
            role.setActiveFlag(0);
            role.setUpdatedDate(new Timestamp(System.currentTimeMillis()));
            roleDAO.updateRole(role);
        }catch (Exception e){
            throw new InternalServerException("Lỗi xóa role");
        }
    }

    @Override
    public List<RoleEntity> findAllRole() {
        return roleDAO.findAll();
    }

    @Override
    public RoleEntity findByRoleName(String roleName) {
        return roleDAO.findRoleByName(roleName);
    }

    @Override
    public RoleEntity findRoleById(int id) {
        return roleDAO.findRoleById(id);
    }
}
