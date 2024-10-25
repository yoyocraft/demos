package org.example.lock;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;
import java.util.concurrent.TimeUnit;

@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.METHOD)
public @interface LockMethod {

    long timeout() default 0;

    TimeUnit unit() default TimeUnit.SECONDS;

    String key() default "";
}
