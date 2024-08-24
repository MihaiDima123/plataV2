package com.plata.Plata.user.repository;

import com.plata.Plata.user.entity.PermissionGroup;
import com.plata.Plata.user.entity.Permissions;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import java.util.Set;

public interface PermissionGroupRepository extends CrudRepository<PermissionGroup, Integer> {
    @Query("select pg.permissionGroupPermissions " +
            "from PermissionGroup pg " +
            "where pg = :permissionGroup")
    Set<Permissions> getPermissionGroupPermissions(PermissionGroup permissionGroup);
}
