package org.example.lock;


import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.ReentrantLock;

public class LockDemo {

    private final ReentrantLock lock = new ReentrantLock();

    // Spring Autowired 的方式注入
    private final LockTemplateSupport lockTemplateSupport = new LockTemplateSupport();

    public void syncMethod2() throws InterruptedException {
        lockTemplateSupport.executeWithLock(1, TimeUnit.MINUTES, this::coreMethod);
    }

    public void syncMethod1() throws InterruptedException {
        boolean locked = lock.tryLock(1, TimeUnit.MINUTES);
        if (!locked) {
            return;
        }

        // 加锁成功的
        try {
            coreMethod();
        } finally {
            lock.unlock();
        }
    }

    @LockMethod(timeout = 1, unit = TimeUnit.MINUTES)
    public void coreMethod() {
        // do sth...
        System.out.println("do sth...");
    }


    public void syncMethod() throws InterruptedException {
        // ...
        boolean locked = lock.tryLock(1, TimeUnit.MINUTES);
        if (!locked) {
            return;
        }

        try {
            System.out.println("do sth...");
        } finally {
            lock.unlock();
        }

        // ...
    }
}
