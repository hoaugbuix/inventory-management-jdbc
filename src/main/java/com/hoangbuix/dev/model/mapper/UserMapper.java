package com.hoangbuix.dev.model.mapper;

import com.hoangbuix.dev.entity.UserEntity;
import com.hoangbuix.dev.model.dto.UserDTO;
import com.hoangbuix.dev.model.request.create.CreateUserReq;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;

public class UserMapper implements RowMapper<UserEntity> {
    @Override
    public UserEntity mapRow(ResultSet resultSet) {
        try {
            UserEntity user = new UserEntity();
            user.setId(resultSet.getInt("id"));
            user.setFirstName(resultSet.getString("first_name"));
            user.setLastName(resultSet.getString("last_name"));
            user.setAvatar(resultSet.getString("avatar"));
            user.setUsername(resultSet.getString("user_name"));
            user.setPassword(resultSet.getString("password"));
            user.setEmail(resultSet.getString("email"));
            user.setActiveFlag(resultSet.getInt("active_flag"));
            user.setCreatedDate(resultSet.getDate("created_date"));
            user.setUpdatedDate(resultSet.getDate("updated_date"));
            return user;
        }catch (SQLException e){
            e.printStackTrace();
            return null;
        }
    }
}
