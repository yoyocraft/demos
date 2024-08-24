package org.example.spring.event1;

import lombok.RequiredArgsConstructor;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;

/**
 * @author yoyocraft
 * @date 2024/08/14
 */
@Component
@RequiredArgsConstructor
public class Initializer {

    private final ApplicationEventPublisher eventPublisher;

    @PostConstruct
    public void setUp() {
        System.out.println("=======> Initializing...");
        eventPublisher.publishEvent(new InitEvent(this, "Initialized"));
    }

}
