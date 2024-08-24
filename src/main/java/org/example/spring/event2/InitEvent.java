package org.example.spring.event2;

import lombok.EqualsAndHashCode;
import lombok.Value;
import org.springframework.context.ApplicationEvent;

/**
 * @author yoyocraft
 * @date 2024/08/14
 */
@EqualsAndHashCode(callSuper = true)
@Value
public class InitEvent extends ApplicationEvent {

    public InitEvent(Object source, String message) {
        super(source);
        this.message = message;
    }

    String message;
}
