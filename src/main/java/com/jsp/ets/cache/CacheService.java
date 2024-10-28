package com.jsp.ets.cache;

import lombok.AllArgsConstructor;
import org.springframework.cache.Cache;
import org.springframework.cache.CacheManager;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class CacheService {
    private final CacheManager cacheManager;

    public void putCache(String cacheName,String key,Object value){
        Cache cache=cacheManager.getCache(cacheName);
        if(cache!=null){
            cache.put(key,value);
        }
    }

    public <T> T getCache(String cacheName,String key,Class<T> type){
        Cache cache=cacheManager.getCache(cacheName);
        if(cache!=null){
            Cache.ValueWrapper wrapper=cache.get(key);
            if(wrapper!=null)
                return type.cast(wrapper.get());
        }
        return null;
    }

    public void evictCache(String cacheName,String key){
        Cache cache=cacheManager.getCache(cacheName);
        if(cache!=null){
            cache.evict(key);
        }
    }

    public void clearCache(String cacheName){
        Cache cache=cacheManager.getCache(cacheName);
        if(cache!=null){
            cache.clear();
        }
    }

}
