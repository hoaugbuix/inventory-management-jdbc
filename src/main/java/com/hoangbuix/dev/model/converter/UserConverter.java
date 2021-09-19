package com.hoangbuix.dev.model.converter;

import com.hoangbuix.dev.entity.UserEntity;
import com.hoangbuix.dev.model.dto.UserDTO;
import org.springframework.stereotype.Component;

import java.sql.Timestamp;

@Component
public class UserConverter {
    public UserEntity toEntity(UserDTO dto) {
        UserEntity entity = new UserEntity();
        entity.setFirstName(dto.getFirstName());
        entity.setLastName(dto.getLastName());
        entity.setAvatar(dto.getAvatar());
        entity.setUsername(dto.getUsername());
        entity.setEmail(dto.getEmail());
        //hash
//        String hash = BCrypt.hashpw(req.getPassword(), BCrypt.gensalt(12));
        entity.setPassword(dto.getPassword());
        entity.setActiveFlag(1);
        entity.setCreatedDate(new Timestamp(System.currentTimeMillis()));
        entity.setUpdatedDate(new Timestamp(System.currentTimeMillis()));
        return entity;
    }

    public UserDTO toDTO(UserEntity entity) {
        UserDTO dto = new UserDTO();
        if (entity.getId() != null) {
            dto.setId(entity.getId());
        }
        dto.setFirstName(entity.getFirstName());
        dto.setLastName(entity.getLastName());
        dto.setAvatar(entity.getAvatar());
        dto.setEmail(entity.getEmail());
        dto.setUsername(entity.getUsername());
        dto.setPassword(entity.getPassword());
//        dto.setRoles(user.getRoles());
        dto.setActiveFlag(entity.getActiveFlag());
        dto.setCreatedDate(entity.getCreatedDate());
        dto.setUpdatedDate(entity.getUpdatedDate());
        return dto;
    }

    public UserEntity toEntity(UserDTO dto, UserEntity entity) {
        return entity;
    }
}
