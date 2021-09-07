package com.hoangbuix.dev.model.dto;

import com.hoangbuix.dev.entity.AuthEntity;
import com.hoangbuix.dev.entity.UserEntity;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Map;
import java.util.Set;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class MenuDTO extends AbstractDto<UserEntity> {
    private int parentId;
    private String url;
    private String name;
    private int orderIndex;
    private Set<AuthEntity> auths;
    //    private List<MenuEntity> child;
//    private String idMenu;
    private Map<Integer, Integer> mapAuth;
}
