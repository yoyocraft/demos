package org.example.demo;

import java.util.concurrent.BrokenBarrierException;
import java.util.concurrent.CyclicBarrier;

public class RaceSimulation {
    public static void main(String[] args) {
        int threadCount = 10;
        CyclicBarrier barrier = new CyclicBarrier(threadCount, () -> {
            System.out.println("All runners are ready, start running!");
        });

        for (int i = 0; i < threadCount; i++) {
            new Thread(new Runner(barrier), "Runner " + (i + 1)).start();
        }
    }

    static class Runner implements Runnable {
        private final CyclicBarrier barrier;

        Runner(CyclicBarrier barrier) {
            this.barrier = barrier;
        }

        @Override
        public void run() {
            try {
                System.out.println(Thread.currentThread().getName() + " is ready.");
                barrier.await(); // 等待其他线程准备好
                runRace();
            } catch (InterruptedException | BrokenBarrierException e) {
                e.printStackTrace();
            }
        }

        private void runRace() {
            System.out.println(Thread.currentThread().getName() + " is running.");
            // 模拟跑步的时间
            try {
                Thread.sleep((long) (Math.random() * 1000));
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.println(Thread.currentThread().getName() + " finished running.");
        }
    }
}
