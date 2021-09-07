package com.hoangbuix.dev.model.converter;

import com.hoangbuix.dev.entity.MenuEntity;
import com.hoangbuix.dev.model.dto.MenuDTO;
import org.springframework.stereotype.Component;

@Component
public class MenuConverter {
    public MenuEntity toEntity(MenuDTO dto) {
        MenuEntity entity = new MenuEntity();
        entity.setId(dto.getId());
        return entity;
    }

    public MenuDTO toDTO(MenuEntity entity) {
        MenuDTO dto = new MenuDTO();
        if (entity.getId() != null) {
            dto.setId(entity.getId());
        }

        return dto;
    }

    public MenuEntity toEntity(MenuDTO dto, MenuEntity entity) {

        return entity;
    }
}
