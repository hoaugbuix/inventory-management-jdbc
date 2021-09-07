package com.hoangbuix.dev.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.*;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Setter
@Getter
@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity(name = "role")
@Table(name = "role")
public class RoleEntity extends BaseEntity {
    @Column(name = "role_name", length = 20)
    private String roleName;

    @Column(name = "description", nullable = false, length = 200)
    private String description;

    @JsonIgnore
    @ManyToMany(fetch = FetchType.LAZY, cascade = CascadeType.ALL, mappedBy = "roles")
    private Set<UserEntity> users = new HashSet<>();

    @JsonIgnore
    @OneToMany(mappedBy = "roles", cascade = CascadeType.ALL)
    private Set<AuthEntity> auths = new HashSet<>();
}
