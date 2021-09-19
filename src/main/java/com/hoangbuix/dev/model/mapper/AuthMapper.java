package com.hoangbuix.dev.model.mapper;

import com.hoangbuix.dev.entity.AuthEntity;
import com.hoangbuix.dev.entity.MenuEntity;
import com.hoangbuix.dev.entity.RoleEntity;

import java.sql.ResultSet;
import java.sql.SQLException;

public class AuthMapper implements RowMapper<AuthEntity> {
    @Override
    public AuthEntity mapRow(ResultSet resultSet) {
        try {
            AuthEntity auth = new AuthEntity();
            auth.setId(resultSet.getInt("id"));
            try {
                RoleEntity role = new RoleEntity();
                role.setId(resultSet.getInt("id"));
                role.setRoleName(resultSet.getString("role_name"));
                auth.setRoles(role);
                MenuEntity menu = new MenuEntity();
                menu.setId(resultSet.getInt("id"));
                auth.setMenus(menu);
            } catch (Exception e) {
                e.printStackTrace();
            }
            auth.setPermission(resultSet.getInt("permission"));
            auth.setActiveFlag(resultSet.getInt("active_flag"));
            auth.setCreatedDate(resultSet.getDate("created_date"));
            auth.setUpdatedDate(resultSet.getDate("updated_date"));
            return auth;
        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        }
    }
}
