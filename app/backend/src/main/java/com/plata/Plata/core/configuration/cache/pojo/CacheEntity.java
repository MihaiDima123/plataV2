package com.plata.Plata.core.configuration.cache.pojo;

import lombok.Builder;
import lombok.Getter;

import javax.cache.configuration.Configuration;

@Getter
@Builder
public class CacheEntity<K, V> {
    private String name;
    private Configuration<K, V> configuration;
}
