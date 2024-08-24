package org.example.threadpool;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.lang.management.ManagementFactory;
import java.lang.management.ThreadInfo;
import java.lang.management.ThreadMXBean;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

/**
 * @author yoyocraft
 * @date 2024/08/10
 */
public class ThreadPoolMonitorDemo {

    private static final Logger LOG = LoggerFactory.getLogger(ThreadPoolMonitorDemo.class);

    private static final int CORE_POOL_SIZE = 4;
    private static final int MAX_POOL_SIZE = 8;
    private static final int KEEP_ALIVE_TIME = 60;
    private static final TimeUnit TIME_UNIT = TimeUnit.SECONDS;
    private static final int QUEUE_CAPACITY = 100;
    private static final double CPU_THRESHOLD = 0.75D;
    private static final BlockingQueue<Runnable> WORK_QUEUE = new LinkedBlockingQueue<>(QUEUE_CAPACITY);

    private static final ThreadPoolExecutor executor = new ThreadPoolExecutor(
            CORE_POOL_SIZE,
            MAX_POOL_SIZE,
            KEEP_ALIVE_TIME,
            TIME_UNIT,
            WORK_QUEUE,
            new ThreadPoolExecutor.CallerRunsPolicy()
    );

    private static final ThreadMXBean THREAD_MX_BEAN = ManagementFactory.getThreadMXBean();

    private static final int TASK_COUNT = 20;

    public static void main(String[] args) {
        Runnable task = () -> {
            try {
                Thread.sleep(1000L);
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
            }
        };

        // 模拟执行任务
        for (int i = 0; i < TASK_COUNT; i++) {
            executor.execute(task);
        }

        // 启动监控线程
        new Thread(() -> {
            while (!executor.isShutdown()) {
                double cpuLoad = getCpuLoad();
                LOG.info("Current CPU load: {}", cpuLoad);

                if (cpuLoad > CPU_THRESHOLD && executor.getActiveCount() < MAX_POOL_SIZE) {
                    int newCorePoolSize = Math.min(executor.getCorePoolSize() + 1, MAX_POOL_SIZE);
                    executor.setCorePoolSize(newCorePoolSize);
                    LOG.info("Increased core pool size to {}", newCorePoolSize);
                } else if (cpuLoad < CPU_THRESHOLD && executor.getActiveCount() > CORE_POOL_SIZE) {
                    int newCorePoolSize = Math.max(executor.getCorePoolSize() - 1, CORE_POOL_SIZE);
                    executor.setCorePoolSize(newCorePoolSize);
                    LOG.info("Decreased core pool size to {}", newCorePoolSize);
                }

                try {
                    // 每 5 秒检查一次
                    Thread.sleep(5000L);
                } catch (InterruptedException e) {
                    Thread.currentThread().interrupt();
                }
            }
        }, "monitor-thread").start();


        executor.shutdown();
    }

    private static double getCpuLoad() {
        long[] threadIds = THREAD_MX_BEAN.getAllThreadIds();
        long totalCpuTime = 0L;
        long totalUserTime = 0L;
        for (long threadId : threadIds) {
            ThreadInfo threadInfo = THREAD_MX_BEAN.getThreadInfo(threadId);
            if (threadInfo == null || Thread.State.WAITING.equals(threadInfo.getThreadState())) {
                continue;
            }

            totalCpuTime += THREAD_MX_BEAN.getThreadCpuTime(threadId);
            totalUserTime += THREAD_MX_BEAN.getThreadUserTime(threadId);
        }
        return (double) totalCpuTime / (totalUserTime + totalCpuTime);
    }


}
