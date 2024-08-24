package com.plata.Plata.core.configuration.cache.impl;

import com.plata.Plata.core.configuration.cache.pojo.CacheEntity;
import com.plata.Plata.core.configuration.cache.CacheProvider;
import com.plata.Plata.user.dto.auth.PermissionDTO;

import javax.cache.configuration.Configuration;
import javax.cache.configuration.MutableConfiguration;
import javax.cache.expiry.CreatedExpiryPolicy;
import javax.cache.expiry.Duration;
import java.util.Set;

public class PermissionGroupCacheProvider implements CacheProvider<Integer, Set<PermissionDTO>> {
    public static final String CACHE_NAME = "permission-group-permissions";

    @Override
    public CacheEntity<Integer, Set<PermissionDTO>> get() {
        return CacheEntity.<Integer, Set<PermissionDTO>>builder()
                .name(CACHE_NAME)
                .configuration(getConfiguration())
                .build();
    }

    private Configuration<Integer, Set<PermissionDTO>> getConfiguration() {
        return new MutableConfiguration<Integer, Set<PermissionDTO>>()
                .setExpiryPolicyFactory(CreatedExpiryPolicy.factoryOf(Duration.ONE_HOUR));
    }
}
