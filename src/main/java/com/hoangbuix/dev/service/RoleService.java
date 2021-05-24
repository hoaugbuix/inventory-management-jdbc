package com.hoangbuix.dev.service;

import com.hoangbuix.dev.entity.RoleEntity;
import com.hoangbuix.dev.model.request.CreateRoleReq;
import com.hoangbuix.dev.model.request.UpdateRoleReq;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface RoleService {
    RoleEntity saveRole(CreateRoleReq req);
    void updateRole(int id, UpdateRoleReq req);
    void deleteRole(int id);
    List<RoleEntity> findAllRole();
    RoleEntity findByRoleName(String roleName);
    RoleEntity findRoleById(int id);
}
