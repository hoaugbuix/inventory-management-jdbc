package com.hoangbuix.dev.model.dto;

import com.hoangbuix.dev.entity.RoleEntity;
import com.hoangbuix.dev.entity.UserEntity;
import com.hoangbuix.dev.entity.UserRoleEntity;
import lombok.*;

import java.util.HashSet;
import java.util.Set;

@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class UserDTO extends AbstractDto<UserEntity> {
    private String firstName;
    private String lastName;
    private String avatar;
    private String email;
    private String username;
    private String password;
    private Set<RoleEntity> roles;
    private Set<UserRoleEntity> userRoles = new HashSet<>();
    private Integer roleID;
}
