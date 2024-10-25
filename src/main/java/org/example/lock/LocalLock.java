package org.example.lock;

import com.google.common.cache.Cache;
import com.google.common.cache.CacheBuilder;

import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class LocalLock {

    private final Cache<String, Lock> lockCache;

    public LocalLock() {
        lockCache = CacheBuilder
                .newBuilder()
                .expireAfterAccess(10, TimeUnit.MINUTES)
                .build();
    }

    public void executeWithLock(String key, long timeout, TimeUnit unit, Runnable task) throws InterruptedException {
        Lock lock = lockCache.getIfPresent(key);
        if (lock == null) {
            lock = new ReentrantLock();
            lockCache.put(key, lock);
        }

        boolean locked = lock.tryLock(timeout, unit);
        if (!locked) {
            return;
        }

        try {
            task.run();
        } finally {
            lock.unlock();
        }
    }
}
