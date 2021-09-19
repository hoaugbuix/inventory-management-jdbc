package com.hoangbuix.dev.service;

import com.hoangbuix.dev.entity.UserRoleEntity;
import com.hoangbuix.dev.model.request.create.CreateUserRoleReq;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface UserRoleService {
    UserRoleEntity save(CreateUserRoleReq req);
    List<UserRoleEntity> findAll();
}
