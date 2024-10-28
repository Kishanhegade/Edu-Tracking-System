package com.jsp.ets.cache;

import org.springframework.cache.CacheManager;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.cache.jcache.JCacheCacheManager;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import javax.cache.Caching;
import javax.cache.spi.CachingProvider;

@Configuration
@EnableCaching
public class CacheConfig {

    @Bean
    CacheManager cacheManager(){
        CachingProvider cachingProvider= Caching.getCachingProvider();
        javax.cache.CacheManager cacheManager=cachingProvider.getCacheManager();
        return new JCacheCacheManager(cacheManager);
    }
}
