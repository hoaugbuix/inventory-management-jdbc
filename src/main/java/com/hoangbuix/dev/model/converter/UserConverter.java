package com.hoangbuix.dev.model.converter;

import com.hoangbuix.dev.entity.UserEntity;
import com.hoangbuix.dev.model.dto.UserDTO;
import org.springframework.stereotype.Component;

@Component
public class UserConverter {
    public UserEntity toEntity(UserDTO dto) {
        UserEntity entity = new UserEntity();

        return entity;
    }

    public UserDTO toDTO(UserEntity entity) {
        UserDTO dto = new UserDTO();
        if (entity.getId() != null) {
            dto.setId(entity.getId());
        }
        return dto;
    }

    public UserEntity toEntity(UserDTO dto, UserEntity entity) {
        return entity;
    }
}
