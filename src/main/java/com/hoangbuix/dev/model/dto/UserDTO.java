package com.hoangbuix.dev.model.dto;

import com.hoangbuix.dev.entity.UserEntity;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

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
//    private Set<RoleEntity> roles;
//    private Set<UserRoleEntity> userRoles = new HashSet<>();
//    private Integer roleID;
}
