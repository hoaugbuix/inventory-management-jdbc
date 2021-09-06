package com.hoangbuix.dev.service;

import com.hoangbuix.dev.entity.UserEntity;
import com.hoangbuix.dev.model.dto.UserDTO;
import com.hoangbuix.dev.model.request.update.UpdateUserReq;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface UserService {
    UserEntity findById(int id);
    UserEntity findUserByUsername(String username);
//    UserEntity findByEmailAndUsername(String email, String username);
    List<UserEntity> getAllUser();
    UserEntity saveUser(UserDTO req);
    UserEntity updateUser(int id, UpdateUserReq req);
    void deleteUser(int id);
}
