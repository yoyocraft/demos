package org.example.spring.event2;

import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Component;

/**
 * @author yoyocraft
 * @date 2024/08/14
 */
@Component
public class InitEventListener {

    @EventListener(InitEvent.class)
    public void onInitEvent(InitEvent event) {
        System.out.println("======> InitEventListener.onInitEvent");
        String message = event.getMessage();
        System.out.println("message = " + message);
    }
}
