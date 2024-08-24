package com.plata.Plata.core.configuration.cache;

import com.plata.Plata.core.configuration.cache.pojo.CacheEntity;

public interface CacheProvider<K, V> {
    CacheEntity<K, V> get();
}
