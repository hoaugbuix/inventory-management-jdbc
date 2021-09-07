package com.hoangbuix.dev.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.*;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Setter
@Getter
@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity(name = "menu")
@Table(name = "menu")
public class MenuEntity extends BaseEntity {
    @Column(name = "parent_id")
    private int parentId;

    @Column(name = "url")
    private String url;

    @Column(name = "name")
    private String name;

    @Column(name = "order_index")
    private int orderIndex;

    @JsonIgnore
    @OneToMany(cascade = CascadeType.ALL, orphanRemoval = true, mappedBy = "menus")
    @EqualsAndHashCode.Exclude
    @ToString.Exclude
    private Set<AuthEntity> auths = new HashSet<>();

}
