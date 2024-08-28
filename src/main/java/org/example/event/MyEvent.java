package org.example.event;

import org.springframework.context.ApplicationEvent;

/**
 * @author yoyocraft
 * @date 2024/08/27
 */
public class MyEvent extends ApplicationEvent {
    public MyEvent(Object source) {
        super(source);
    }
}
