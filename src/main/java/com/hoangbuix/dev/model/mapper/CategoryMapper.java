package com.hoangbuix.dev.model.mapper;

import com.hoangbuix.dev.entity.CategoryEntity;

import java.sql.ResultSet;
import java.sql.SQLException;

public class CategoryMapper implements RowMapper<CategoryEntity> {
    @Override
    public CategoryEntity mapRow(ResultSet resultSet) {
        try {
            CategoryEntity category = new CategoryEntity();
            category.setId(resultSet.getInt("id"));
            category.setCode(resultSet.getString("code"));
            category.setName(resultSet.getString("name"));
            category.setDescription(resultSet.getString("description"));
            category.setActiveFlag(resultSet.getInt("active_flag"));
            category.setCreatedDate(resultSet.getDate("created_date"));
            category.setUpdatedDate(resultSet.getDate("updated_date"));
            return category;
        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        }
    }
}
