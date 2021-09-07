package com.hoangbuix.dev.service;

import com.hoangbuix.dev.model.dto.MenuDTO;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface MenuService {
    void changeStatus(int id);

    void updatePermission(int roleId, int menuId, int permission);

    List<MenuDTO> findALl();
}
