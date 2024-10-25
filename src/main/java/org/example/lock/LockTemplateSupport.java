package org.example.lock;

import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.ReentrantLock;
import java.util.function.Supplier;

/**
 * 加锁的模板代码
 */
public class LockTemplateSupport {


    private final ReentrantLock lock = new ReentrantLock();


    public void executeWithLock(long timeout, TimeUnit unit, Runnable task) throws InterruptedException {
        boolean locked = lock.tryLock(timeout, unit);
        if (!locked) {
            return;
        }

        // 加锁成功的
        try {
            task.run();
        } finally {
            lock.unlock();
        }
    }

    public <T> T executeWithLock(long timeout, TimeUnit unit, Supplier<T> task) throws InterruptedException {
        boolean locked = lock.tryLock(timeout, unit);
        if (!locked) {
            // or throw ex
            return null;
        }

        // 加锁成功的
        try {
            return task.get();
        } finally {
            lock.unlock();
        }
    }

}
