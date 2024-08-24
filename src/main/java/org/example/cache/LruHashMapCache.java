package org.example.cache;

import java.util.LinkedHashMap;
import java.util.Map.Entry;

/**
 * @author yoyocraft
 * @date 2024/08/03
 */
public class LruHashMapCache<K, V> extends LinkedHashMap<K, V> {

    private static final long serialVersionUID = 6564296515247140622L;
    private final int maxSize;

    public LruHashMapCache(boolean accessOrder, int maxSize) {
        super(16, 0.75f, accessOrder);
        this.maxSize = maxSize;
    }

    @Override
    protected boolean removeEldestEntry(Entry<K, V> eldest) {
        return this.size() > maxSize;
    }
}
