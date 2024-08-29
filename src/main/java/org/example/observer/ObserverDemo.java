package org.example.observer;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.util.List;

/**
 * @author yoyocraft
 * @date 2024/08/29
 */
@SpringBootApplication
public class ObserverDemo implements CommandLineRunner {

    @Autowired
    private List<Observer> observers;


    public static void main(String[] args) {
        SpringApplication.run(ObserverDemo.class, args);
    }


    @Override
    public void run(String... args) throws Exception {
        sendMsg("bizType1", "业务1的内容");
        sendMsg("bizType2", "业务2的内容");
    }

    private void sendMsg(String bizType, String content) {
        observers.forEach(observer -> observer.notify(bizType, content));
    }
}
