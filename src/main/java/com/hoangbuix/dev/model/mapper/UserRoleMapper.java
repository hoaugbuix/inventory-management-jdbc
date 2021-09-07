package com.hoangbuix.dev.model.mapper;

import com.hoangbuix.dev.entity.UserRoleEntity;

import java.sql.ResultSet;
import java.sql.SQLException;

public class UserRoleMapper implements RowMapper<UserRoleEntity> {
    @Override
    public UserRoleEntity mapRow(ResultSet resultSet) {
        try {
            UserRoleEntity userRole = new UserRoleEntity();
            userRole.setId(resultSet.getInt("id"));
            userRole.setUserId(resultSet.getInt("user_id"));
            userRole.setRoleId(resultSet.getInt("role_id"));
            userRole.setActiveFlag(resultSet.getInt("active_flag"));
            userRole.setCreatedDate(resultSet.getDate("created_date"));
            userRole.setUpdatedDate(resultSet.getDate("updated_date"));
            return userRole;
        } catch (SQLException e) {
            return null;
        }
    }
}
