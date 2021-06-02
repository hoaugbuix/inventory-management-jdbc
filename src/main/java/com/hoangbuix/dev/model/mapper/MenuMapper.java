package com.hoangbuix.dev.model.mapper;

import com.hoangbuix.dev.entity.MenuEntity;

import java.sql.ResultSet;
import java.sql.SQLException;

public class MenuMapper implements RowMapper<MenuEntity> {
    @Override
    public MenuEntity mapRow(ResultSet resultSet) {
        try {
            MenuEntity menu = new MenuEntity();
            menu.setId(resultSet.getInt("id"));
            return menu;
        }catch (SQLException e) {
            e.printStackTrace();
            return null;
        }
    }
}
