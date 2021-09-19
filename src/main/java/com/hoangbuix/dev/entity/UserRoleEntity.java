package com.hoangbuix.dev.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.*;

import javax.persistence.*;

@Setter
@Getter
@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity(name = "user_role")
@Table(name = "user_role")
public class UserRoleEntity extends BaseEntity {
    @JsonIgnore
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn( name = "user_id")
    private UserEntity users;

    @JsonIgnore
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn( name = "role_id")
    private RoleEntity roles;
//    @Column(name = "user_id")
//    private Integer userId;
//
//    @Column(name = "role_id")
//    private Integer roleId;
}
