package org.example.cache;

import lombok.AllArgsConstructor;
import lombok.Data;

/**
 * @author yoyocraft
 * @date 2024/08/03
 */
@Data
@AllArgsConstructor
public class CacheItem<V> {

    private V data;
    private long expireAt;
}
