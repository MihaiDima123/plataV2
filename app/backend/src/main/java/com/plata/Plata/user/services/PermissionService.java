package com.plata.Plata.user.services;

import com.plata.Plata.user.entity.PermissionGroup;
import com.plata.Plata.user.entity.Permissions;
import com.plata.Plata.user.repository.PermissionGroupRepository;
import org.springframework.stereotype.Service;

import java.util.Set;

@Service
public class PermissionService {
    private final PermissionGroupRepository permissionGroupRepository;

    public PermissionService(PermissionGroupRepository permissionGroupRepository) {
        this.permissionGroupRepository = permissionGroupRepository;
    }

    // TODO: Cache that
    public Set<Permissions> getPermissionGroupPermissions(PermissionGroup permissionGroup) {
        return permissionGroupRepository.getPermissionGroupPermissions(permissionGroup);
    }
}
