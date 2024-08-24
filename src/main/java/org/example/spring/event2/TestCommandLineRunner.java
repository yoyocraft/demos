package org.example.spring.event2;

import lombok.RequiredArgsConstructor;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.stereotype.Component;

/**
 * @author yoyocraft
 * @date 2024/08/14
 */
@Component
@RequiredArgsConstructor
public class TestCommandLineRunner implements CommandLineRunner {
    private final ApplicationEventPublisher eventPublisher;

    @Override
    public void run(String... args) throws Exception {
        System.out.println("=======> Running...");
        eventPublisher.publishEvent(new InitEvent(this, "Running"));
    }
}
