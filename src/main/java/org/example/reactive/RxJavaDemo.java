package org.example.reactive;

import io.reactivex.Flowable;
import io.reactivex.schedulers.Schedulers;

import java.util.concurrent.TimeUnit;

/**
 * @author yoyocraft
 * @date 2024/09/17
 */
public class RxJavaDemo {

    public static void main(String[] args) throws InterruptedException {
        fn1();
    }

    public static void fn1() throws InterruptedException {
        // 创建一个流，每秒发射一个递增的整数（数据流变化）
        Flowable<Long> flowable = Flowable.interval(1, TimeUnit.SECONDS)
                .map(i -> i + 1)
                .subscribeOn(Schedulers.io());

        // 订阅 Flowable 流，并打印每个接受到的数字
        flowable.subscribeOn(Schedulers.io())
                .doOnNext(item -> System.out.println(item.toString()))
                .subscribe();

        // 等待一段时间
        Thread.sleep(10000L);
    }
}
