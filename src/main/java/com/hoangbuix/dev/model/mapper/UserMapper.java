package com.hoangbuix.dev.model.mapper;

import com.hoangbuix.dev.entity.RoleEntity;
import com.hoangbuix.dev.entity.UserEntity;
import com.hoangbuix.dev.model.dto.UserDTO;
import com.hoangbuix.dev.model.request.CreateUserReq;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.*;

public class UserMapper implements RowMapper<UserEntity> {
    @Override
    public UserEntity mapRow(ResultSet resultSet) {
        try {
            UserEntity user = new UserEntity();
            user.setId(resultSet.getInt("id"));
            user.setFirstName(resultSet.getString("first_name"));
            user.setLastName(resultSet.getString("last_name"));
            user.setAvatar(resultSet.getString("avatar"));
            user.setEmail(resultSet.getString("email"));
            user.setUsername(resultSet.getString("user_name"));
            user.setPassword(resultSet.getString("password"));
            user.setActiveFlag(resultSet.getInt("active_flag"));
            user.setCreatedDate(resultSet.getDate("created_date"));
            user.setUpdatedDate(resultSet.getDate("updated_date"));
            return user;
        }catch (SQLException e){
            e.printStackTrace();
            return null;
        }
    }

    public static UserEntity toUser(CreateUserReq req){
        try {
            UserEntity user = new UserEntity();
            user.setFirstName(req.getFirstName());
            user.setLastName(req.getLastName());
            user.setAvatar(req.getAvatar());
            user.setEmail(req.getEmail());
            user.setUsername(req.getUsername());
            //hash
//        String hash = BCrypt.hashpw(req.getPassword(), BCrypt.gensalt(12));
            user.setPassword(req.getPassword());
            user.setActiveFlag(1);
            user.setCreatedDate(new Timestamp(System.currentTimeMillis()));
            user.setUpdatedDate(new Timestamp(System.currentTimeMillis()));
            return user;
        }catch (Exception e){
            e.printStackTrace();
            return null;
        }
    }

    public static UserDTO toDto(UserEntity user){
        UserDTO tmp = new UserDTO();
        if (user.getId() != null){
            tmp.setId(user.getId());
        }
        tmp.setFirstName(user.getFirstName());
        tmp.setLastName(user.getLastName());
        tmp.setAvatar(user.getAvatar());
        tmp.setEmail(user.getEmail());
        tmp.setUsername(user.getUsername());
        tmp.setPassword(user.getPassword());
//        tmp.setRoles(user.getRoles());
        tmp.setActiveFlag(user.getActiveFlag());
        tmp.setCreatedDate(user.getCreatedDate());
        tmp.setUpdatedDate(user.getUpdatedDate());

        return tmp;
    }
}
