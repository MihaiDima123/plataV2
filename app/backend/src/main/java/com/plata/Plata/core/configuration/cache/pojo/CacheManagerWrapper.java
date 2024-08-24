package com.plata.Plata.core.configuration.cache.pojo;

import org.springframework.cache.jcache.JCacheCacheManager;

import javax.cache.CacheManager;
import javax.cache.Caching;
import javax.cache.spi.CachingProvider;
import java.util.HashSet;
import java.util.Set;

public class CacheManagerWrapper {
    private final CacheManager cacheManager;
    private final Set<CacheEntity<?,?>> cacheEntitySet = new HashSet<>();

    public CacheManagerWrapper() {
        CachingProvider cachingProvider = Caching.getCachingProvider();
        this.cacheManager = cachingProvider.getCacheManager();
    }

    public void addCache(CacheEntity<?, ?> cacheEntity) {
        cacheEntitySet.add(cacheEntity);

    }

    public JCacheCacheManager newJcacheCacheManagerInstance() {
        cacheEntitySet.forEach(cacheEntity ->
                cacheManager.createCache(cacheEntity.getName(), cacheEntity.getConfiguration()));

        return new JCacheCacheManager(cacheManager);
    }
}
