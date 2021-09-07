package com.hoangbuix.dev.controller;

import com.hoangbuix.dev.entity.AuthEntity;
import com.hoangbuix.dev.entity.RoleEntity;
import com.hoangbuix.dev.model.converter.MenuConverter;
import com.hoangbuix.dev.model.dto.MenuDTO;
import com.hoangbuix.dev.model.request.create.CreateAuthFormReq;
import com.hoangbuix.dev.service.MenuService;
import com.hoangbuix.dev.service.RoleService;
import lombok.RequiredArgsConstructor;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;

@RestController
@RequestMapping("/api/v1")
@RequiredArgsConstructor
public class MenuController {
    private final Logger log = Logger.getLogger(MenuController.class);
    @Autowired
    private MenuService menuService;

    @Autowired
    private RoleService roleService;
    @Autowired
    private MenuConverter menuConverter;

    @GetMapping("/menu/list")
    public ResponseEntity<?> findAll() {
        List<RoleEntity> roles = roleService.findAllRole();
        List<MenuDTO> menus = menuService.findALl();
        Collections.sort(roles, (o1, o2) -> o1.getId() - o2.getId());
        for (MenuDTO item : menus) {
            Map<Integer, Integer> mapAuth = new TreeMap<>();
            for (RoleEntity role : roles) {
                mapAuth.put(role.getId(), 0); // 1-0, 2-0, 3-0
            }
            for (Object obj : item.getAuths()) {
                AuthEntity auth = (AuthEntity) obj;
                mapAuth.put(auth.getRoles().getId(), auth.getPermission()); //1-1, 2-0, 3-0
            }
            item.setMapAuth(mapAuth);
        }
        return new ResponseEntity<>(menus, HttpStatus.OK);
    }


    @GetMapping("/menu/change-status/{id}")
    private ResponseEntity<?> change(@PathVariable("id") int id) {
        try {
            menuService.changeStatus(id);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return ResponseEntity.ok("Change success!");
    }

    @PostMapping("/menu/update-permission")
    private ResponseEntity<?> updatePermission(CreateAuthFormReq authForm) {
        try {
            menuService.updatePermission(authForm.getRoleId(), authForm.getMenuId(), authForm.getPermission());
        } catch (Exception e) {
            e.printStackTrace();
        }
        return new ResponseEntity<>("Update permission success!", HttpStatus.OK);
    }

}
