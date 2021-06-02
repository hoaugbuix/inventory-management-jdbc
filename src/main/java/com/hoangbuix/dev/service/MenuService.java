package com.hoangbuix.dev.service;

import com.hoangbuix.dev.entity.MenuEntity;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface MenuService {
    void changeStatus(int id);
    void updatePermission(int roleId, int menuId, int permission );
    List<MenuEntity> findALl();

}
