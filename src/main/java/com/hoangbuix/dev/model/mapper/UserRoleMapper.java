package com.hoangbuix.dev.model.mapper;

import com.hoangbuix.dev.entity.RoleEntity;
import com.hoangbuix.dev.entity.UserEntity;
import com.hoangbuix.dev.entity.UserRoleEntity;

import java.sql.ResultSet;
import java.sql.SQLException;

public class UserRoleMapper implements RowMapper<UserRoleEntity> {
    @Override
    public UserRoleEntity mapRow(ResultSet resultSet) {
        try {
            UserRoleEntity userRole = new UserRoleEntity();
            userRole.setId(resultSet.getInt("id"));
            try {
                UserEntity user = new UserEntity();
                user.setId(resultSet.getInt("id"));
                user.setEmail(resultSet.getString("email"));
                userRole.setUsers(user);
                RoleEntity role = new RoleEntity();
                role.setId(resultSet.getInt("id"));
                role.setRoleName(resultSet.getString("role_name"));
                userRole.setRoles(role);
            }catch (SQLException e){
                return null;
            }
            userRole.setActiveFlag(resultSet.getInt("active_flag"));
            userRole.setCreatedDate(resultSet.getDate("created_date"));
            userRole.setUpdatedDate(resultSet.getDate("updated_date"));
            return userRole;
        }catch (SQLException e){
            return null;
        }
    }
}
