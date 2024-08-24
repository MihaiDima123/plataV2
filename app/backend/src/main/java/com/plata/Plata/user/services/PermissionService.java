package com.plata.Plata.user.services;

import com.plata.Plata.core.configuration.cache.impl.PermissionGroupCacheProvider;
import com.plata.Plata.user.dto.auth.PermissionDTO;
import com.plata.Plata.user.repository.PermissionGroupRepository;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import java.util.Set;
import java.util.stream.Collectors;

@Service
public class PermissionService {
    private final PermissionGroupRepository permissionGroupRepository;

    public PermissionService(PermissionGroupRepository permissionGroupRepository) {
        this.permissionGroupRepository = permissionGroupRepository;
    }

    @Cacheable(cacheNames = {PermissionGroupCacheProvider.CACHE_NAME})
    public Set<PermissionDTO> getPermissionGroupPermissionsByPermissionGroupId(Integer permissionGroupId) {
        return permissionGroupRepository.getPermissionGroupPermissions(permissionGroupId)
                .stream().map(PermissionDTO::from)
                .collect(Collectors.toSet());
    }
}
