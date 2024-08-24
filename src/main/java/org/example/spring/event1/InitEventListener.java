package org.example.spring.event1;

import org.springframework.context.ApplicationListener;
import org.springframework.stereotype.Component;

/**
 * @author yoyocraft
 * @date 2024/08/14
 */
@Component
public class InitEventListener implements ApplicationListener<InitEvent> {

    @Override
    public void onApplicationEvent(InitEvent event) {
        System.out.println("======> InitEventListener.onInitEvent");
        String message = event.getMessage();
        System.out.println("message = " + message);
    }
}
