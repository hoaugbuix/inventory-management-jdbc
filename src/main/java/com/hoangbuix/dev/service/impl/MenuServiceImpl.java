package com.hoangbuix.dev.service.impl;

import com.hoangbuix.dev.dao.AuthDAO;
import com.hoangbuix.dev.dao.MenuDAO;
import com.hoangbuix.dev.entity.AuthEntity;
import com.hoangbuix.dev.entity.MenuEntity;
import com.hoangbuix.dev.entity.RoleEntity;
import com.hoangbuix.dev.service.MenuService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.sql.Timestamp;
import java.util.List;

@Component
public class MenuServiceImpl implements MenuService {
    @Autowired
    private MenuDAO<MenuEntity> menuDAO;

    @Autowired
    private AuthDAO<AuthEntity> authDAO;

    @Override
    public void changeStatus(int id) {
        MenuEntity menu = menuDAO.findById(id);
        if (menu != null){
            menu.setActiveFlag(menu.getActiveFlag()==1 ? 0 : 1);
            menuDAO.save(menu);
            return;
        }
    }

    @Override
    public void updatePermission(int roleId, int menuId, int permission) {
        AuthEntity auth = authDAO.findByRoleIdAndMenuId(roleId, menuId);
        if (auth != null){
            auth.setPermission(permission);
            authDAO.update(auth);
        }else {
            if (permission == 1){
                auth = new AuthEntity();
                auth.setActiveFlag(1);
                RoleEntity role = new RoleEntity();
                role.setId(roleId);
                MenuEntity menu = new MenuEntity();
                menu.setId(menuId);
                auth.setRoles(role);
                auth.setMenus(menu);
                auth.setPermission(1);
                auth.setCreatedDate(new Timestamp(System.currentTimeMillis()));
                auth.setUpdatedDate(new Timestamp(System.currentTimeMillis()));
                authDAO.save(auth);
            }
        }
    }

    @Override
    public List<MenuEntity> findALl() {
        return menuDAO.findAll();
    }
}
