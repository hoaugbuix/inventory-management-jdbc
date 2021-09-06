package com.hoangbuix.dev.entity;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
@Entity(name = "role")
@Table(name = "role")
public class RoleEntity extends BaseEntity{
    @Column(name = "role_name", length = 20)
    private String roleName;

    @Column(name = "description", nullable = false, length = 200)
    private String description;

    @ManyToMany(fetch = FetchType.LAZY, cascade = CascadeType.ALL, mappedBy = "roles")
    private Set<UserEntity> users = new HashSet<>();

    @OneToMany(mappedBy = "roles", cascade = CascadeType.ALL)
    private Set<AuthEntity> auths = new HashSet<>();
}
