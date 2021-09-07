package com.hoangbuix.dev.entity;

import lombok.*;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

@Setter
@Getter
@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity(name = "user_role")
@Table(name = "user_role")
public class UserRoleEntity extends BaseEntity {
    //    @ManyToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
//    @JoinColumn(columnDefinition = "integer", name = "user_id")
//    private UserEntity users;
//
//    @ManyToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
//    @JoinColumn(columnDefinition = "integer", name = "role_id")
//    private RoleEntity roles;
    @Column(name = "user_id")
    private Integer userId;

    @Column(name = "role_id")
    private Integer roleId;
}
