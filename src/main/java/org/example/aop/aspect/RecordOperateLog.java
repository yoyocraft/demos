package org.example.aop.aspect;

import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;
import org.example.aop.converter.OperateLogConverter;

/**
 * @author yoyocraft
 * @date 2024/07/31
 */
@Documented
@Target(value = {ElementType.METHOD})
@Retention(RetentionPolicy.RUNTIME)
public @interface RecordOperateLog {

    String desc() default "";

    @SuppressWarnings("rawtypes") Class<? extends OperateLogConverter> converter();

}
