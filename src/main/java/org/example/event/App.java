package org.example.event;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationEventPublisher;

import javax.annotation.PostConstruct;

/**
 * @author yoyocraft
 * @date 2024/08/27
 */
@SpringBootApplication
public class App implements CommandLineRunner {

    @Autowired
    private ApplicationEventPublisher eventPublisher;

    public static void main(String[] args) {
        SpringApplication.run(App.class, args);
    }

    // @PostConstruct
    // public void init() {
    //     eventPublisher.publishEvent(new MyEvent("execute init method "));
    // }

    @Override
    public void run(String... args) throws Exception {
        eventPublisher.publishEvent(new MyEvent("execute init method "));
    }
}
