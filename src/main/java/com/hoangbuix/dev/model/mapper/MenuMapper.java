package com.hoangbuix.dev.model.mapper;

import com.hoangbuix.dev.entity.AuthEntity;
import com.hoangbuix.dev.entity.RoleEntity;
import com.hoangbuix.dev.model.dto.MenuDTO;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Collections;

public class MenuMapper implements RowMapper<MenuDTO> {
    @Override
    public MenuDTO mapRow(ResultSet resultSet) {
        try {
            MenuDTO menu = new MenuDTO();
            menu.setId(resultSet.getInt("id"));
            menu.setParentId(resultSet.getInt("parent_id"));
            menu.setUrl(resultSet.getString("url"));
            menu.setName(resultSet.getString("name"));
            menu.setOrderIndex(resultSet.getInt("order_index"));
            menu.setActiveFlag(resultSet.getInt("active_flag"));
            menu.setCreatedDate(resultSet.getDate("created_date"));
            menu.setUpdatedDate(resultSet.getDate("updated_date"));
            try {
                RoleEntity role = new RoleEntity();
                role.setId(resultSet.getInt("id"));
                AuthEntity auth = new AuthEntity();
                auth.setId(resultSet.getInt("id"));
                auth.setPermission(resultSet.getInt("permission"));
                auth.setActiveFlag(resultSet.getInt("active_flag"));
                auth.setCreatedDate(resultSet.getDate("created_date"));
                auth.setUpdatedDate(resultSet.getDate("updated_date"));
                auth.setRoles(role);
                menu.setAuths(Collections.singleton(auth));
            } catch (Exception e1) {
                e1.printStackTrace();
            }
            return menu;
        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        }
    }

}
