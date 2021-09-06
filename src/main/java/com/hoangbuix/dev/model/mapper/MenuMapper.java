package com.hoangbuix.dev.model.mapper;

import com.hoangbuix.dev.entity.AuthEntity;
import com.hoangbuix.dev.entity.MenuEntity;
import com.hoangbuix.dev.model.dto.MenuDTO;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Set;

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
            return menu;
        }catch (SQLException e) {
            e.printStackTrace();
            return null;
        }
    }

}
