package org.example.juc;

import lombok.Getter;

import java.util.concurrent.atomic.AtomicInteger;

public class ThreadSafe {

    public static void main(String[] args) {
        // 基于AtomicInteger实现线程安全
        byAtomicInteger();
        // 基于synchronized关键字实现线程安全
        bySynchronized();
    }

    private static void byAtomicInteger() {
        AtomicInteger count = new AtomicInteger(0);
        // 创建多个线程并执行递增操作
        for (int i = 0; i < 5; i++) {
            new Thread(() -> {
                for (int j = 0; j < 1000; j++) {
                    count.incrementAndGet();
                }
            }).start();
        }
        // 等待所有线程执行完毕
        try {
            Thread.sleep(2000);
        } catch (InterruptedException ignored) {
        }
        System.out.println("AtomicInteger Count: " + count.get());
    }

    private static void bySynchronized() {
        Counter counter = new Counter();
        // 创建多个线程并执行递增操作
        for (int i = 0; i < 5; i++) {
            new Thread(() -> {
                for (int j = 0; j < 1000; j++) {
                    counter.increment();
                }
            }).start();
        }
        // 等待所有线程执行完毕
        try {
            Thread.sleep(2000);
        } catch (InterruptedException ignored) {
        }
        System.out.println("Synchronized Count: " + counter.getCount());
    }

    /**
     * 线程安全计数器
     */
    @Getter
    public static class Counter {
        private int count = 0;

        public synchronized void increment() {
            count++;
        }

    }
}
