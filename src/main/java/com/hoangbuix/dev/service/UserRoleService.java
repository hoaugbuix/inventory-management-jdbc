package com.hoangbuix.dev.service;

import com.hoangbuix.dev.entity.UserRoleEntity;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface UserRoleService {
    List<UserRoleEntity> findAll();
}
