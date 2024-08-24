package com.plata.Plata.user.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.JoinTable;
import jakarta.persistence.ManyToMany;
import lombok.Getter;
import lombok.Setter;

import java.util.HashSet;
import java.util.Set;

@Entity
@Getter
@Setter
public class PermissionGroup {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    private String name;

    @ManyToMany(fetch = FetchType.LAZY)
    @JoinTable(
            joinColumns = { @JoinColumn(name = "permission_group_id") },
            inverseJoinColumns = { @JoinColumn(name = "permission_id") },
            name = "permission_group_permissions"
    )
    private Set<Permissions> permissionGroupPermissions = new HashSet<>();
}
