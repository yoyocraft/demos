package org.example.event;

import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Component;

/**
 * @author yoyocraft
 * @date 2024/08/27
 */
@Component
public class AnnotationEventListener {

    @EventListener(MyEvent.class)
    public void onMyEvent(MyEvent event) {
        System.out.println("annotation listener source: " + event.getSource());
    }
}
