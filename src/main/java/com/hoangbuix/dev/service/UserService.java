package com.hoangbuix.dev.service;

import com.hoangbuix.dev.entity.UserEntity;
import com.hoangbuix.dev.model.request.CreateUserReq;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface UserService {
    UserEntity findById(int id);
    UserEntity findUserByUsername(String username);
//    UserEntity findByEmailAndUsername(String email, String username);
    List<UserEntity> getAllUser();
    UserEntity saveUser(CreateUserReq req);
    UserEntity deleteUser(int id);

    UserEntity findAll();
}
