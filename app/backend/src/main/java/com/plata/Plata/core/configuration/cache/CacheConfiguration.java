package com.plata.Plata.core.configuration.cache;

import com.plata.Plata.core.configuration.cache.impl.PermissionGroupCacheProvider;
import com.plata.Plata.core.configuration.cache.pojo.CacheManagerWrapper;
import com.plata.Plata.user.dto.auth.PermissionDTO;
import org.springframework.cache.CacheManager;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.Set;

@Configuration
@EnableCaching
public class CacheConfiguration {
    private final CacheProvider<Integer, Set<PermissionDTO>> permissionsCacheProvider = new PermissionGroupCacheProvider();

    @Bean
    public CacheManager cacheManager() {
        var javaxCacheManagerWrapper = new CacheManagerWrapper();

        javaxCacheManagerWrapper.addCache(permissionsCacheProvider.get());

        return javaxCacheManagerWrapper.newJcacheCacheManagerInstance();
    }
}
