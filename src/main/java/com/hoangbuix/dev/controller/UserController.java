package com.hoangbuix.dev.controller;

import com.hoangbuix.dev.entity.UserEntity;
import com.hoangbuix.dev.model.converter.UserConverter;
import com.hoangbuix.dev.model.dto.UserDTO;
import com.hoangbuix.dev.service.UserRoleService;
import com.hoangbuix.dev.service.UserService;
import lombok.RequiredArgsConstructor;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1")
@RequiredArgsConstructor
public class UserController {
    final Logger log = Logger.getLogger(UserController.class);
    @Autowired
    private UserService userService;

    @Autowired
    private UserConverter userConverter;

    @Autowired
    private UserRoleService userRoleService;

//    @GetMapping("/user-role")
//    public List<UserRoleEntity> getAllUserRole(){
//        return userRoleService.findAll();
//    }

    @GetMapping("/user/find-by-id/{id}")
    public UserEntity getUserById(@PathVariable int id) {
        UserEntity user = userService.findById(id);
        return user;
    }

    @GetMapping("/user/find-by-username/{username}")
    public UserEntity getUserByUsername(@PathVariable String username) {
        UserEntity user = userService.findUserByUsername(username);
        return user;
    }

    @GetMapping("/user/list")
    public List<UserEntity> getAllUser() {
        return userService.getAllUser();
    }

    @PostMapping("/user/save")
    public ResponseEntity<?> saveUser(@RequestBody UserDTO req) {
        UserEntity result = userService.saveUser(req);
        return new ResponseEntity<>(userConverter.toDTO(result), HttpStatus.OK);
    }
}
