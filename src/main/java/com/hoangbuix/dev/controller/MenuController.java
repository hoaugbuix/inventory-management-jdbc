package com.hoangbuix.dev.controller;

import com.hoangbuix.dev.entity.AuthEntity;
import com.hoangbuix.dev.entity.MenuEntity;
import com.hoangbuix.dev.entity.RoleEntity;
import com.hoangbuix.dev.service.MenuService;
import com.hoangbuix.dev.service.RoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.*;

@Controller
public class MenuController {
    @Autowired
    private MenuService menuService;

    @Autowired
    private RoleService roleService;

    @GetMapping("/menu/list")
    public ResponseEntity<?> findAll(){
        List<RoleEntity> roles = roleService.findAllRole();
        List<MenuEntity> menus = menuService.findALl();
        Collections.sort(roles, (o1, o2) -> o1.getId() - o2.getId());
        for (MenuEntity item : menus){
            Map<Integer, Integer> mapAuth = new TreeMap<>();
            for (RoleEntity role : roles){
                mapAuth.put(role.getId(), 0); // 1-0, 2-0, 3-0
            }
            for (Object obj : item.getAuths()){
                AuthEntity auth = (AuthEntity) obj;
                mapAuth.put(auth.getRoles().getId(), auth.getPermission()); //1-1, 2-0, 3-0
            }
            item.setAuths((Set<AuthEntity>) mapAuth);
        }

        return new ResponseEntity<>(menus, HttpStatus.OK);
    }
}
