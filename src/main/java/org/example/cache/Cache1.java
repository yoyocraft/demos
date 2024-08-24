package org.example.cache;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.TimeUnit;

/**
 * @author yoyocraft
 * @date 2024/08/03
 */
public class Cache1<K, V> {

    private Map<K, CacheItem<V>> cache = new HashMap<>();

    public void expireAfter(K key, int timeInterval, TimeUnit timeUnit) {
        CacheItem<V> item = cache.get(key);
        if (item == null) {
            return;
        }

        long expireAt = System.currentTimeMillis() + timeUnit.toMillis(timeInterval);
        item.setExpireAt(expireAt);
    }

    public void put(K ket, V value, int timeInterval, TimeUnit timeUnit) {
        long expireAt = System.currentTimeMillis() + timeUnit.toMillis(timeInterval);
        cache.put(ket, new CacheItem<>(value, expireAt));
    }

}
