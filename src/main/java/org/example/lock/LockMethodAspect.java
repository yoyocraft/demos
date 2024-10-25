package org.example.lock;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

import java.lang.reflect.Method;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.ReentrantLock;

@Aspect
@Component
@Order(1)
public class LockMethodAspect {

    private final ReentrantLock lock = new ReentrantLock();

    @Pointcut("@execution(org.example.lock.LockMethod)")
    public void pointCut() {
    }

    @Around("pointCut()")
    public Object executeWithMethod(ProceedingJoinPoint joinPoint) throws Throwable {
        MethodSignature methodSignature = (MethodSignature) joinPoint.getSignature();
        Method tobeLockedMethod = methodSignature.getMethod();
        LockMethod lockMethodAnno = tobeLockedMethod.getAnnotation(LockMethod.class);

        long timeout = lockMethodAnno.timeout();
        TimeUnit unit = lockMethodAnno.unit();

        // 正常加锁

        boolean locked = lock.tryLock(timeout, unit);
        if (!locked) {
            // or throw ex
            return null;
        }

        // 加锁成功的
        try {
            return joinPoint.proceed();
        } finally {
            lock.unlock();
        }

    }
}
