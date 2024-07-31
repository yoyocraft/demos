package org.example.aop.aspect;

import com.google.common.cache.CacheBuilder;
import com.google.common.cache.CacheLoader;
import com.google.common.cache.LoadingCache;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import java.lang.reflect.Method;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.ThreadPoolExecutor.DiscardPolicy;
import java.util.concurrent.TimeUnit;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.aspectj.lang.reflect.MethodSignature;
import org.example.aop.converter.OperateLogConverter;
import org.example.aop.log.OperateLog;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

/**
 * @author yoyocraft
 * @date 2024/08/01
 */
@Aspect
@Component
@SuppressWarnings({"rawtypes", "unchecked"})
public class OperateLogAspect {

    private static final Logger LOGGER = LoggerFactory.getLogger(OperateLogAspect.class);

    private static final Gson GSON = new GsonBuilder().create();

    private final ThreadPoolExecutor operateLogThreadPoolExecutor = new ThreadPoolExecutor(
            1,
            1,
            0L,
            TimeUnit.MILLISECONDS,
            new LinkedBlockingQueue<>(100),
            new DiscardPolicy()
    );

    private static final LoadingCache<Class<? extends OperateLogConverter>, OperateLogConverter> CONVERTER_CACHE
            = CacheBuilder.newBuilder()
            .maximumSize(50)
            .build(new CacheLoader<Class<? extends OperateLogConverter>, OperateLogConverter>() {
                @Override
                public OperateLogConverter load(Class<? extends OperateLogConverter> clazz)
                        throws Exception {
                    return clazz.newInstance();
                }
            });


    /**
     * 定义切点 -> 横切逻辑 -> 织入（Spring完成）
     */
    @Pointcut("@annotation(org.example.aop.aspect.RecordOperateLog)")
    public void pointCut() {
    }

    @Around("pointCut()")
    public Object recordOperateLogAspect(ProceedingJoinPoint joinPoint) throws Throwable {
        Object result = joinPoint.proceed();

        operateLogThreadPoolExecutor.execute(() -> {
            MethodSignature methodSignature = (MethodSignature) joinPoint.getSignature();

            Method invokeMethod = methodSignature.getMethod();
            RecordOperateLog recordOperateLog = invokeMethod.getAnnotation(RecordOperateLog.class);
            Class<? extends OperateLogConverter> converterClass = recordOperateLog.converter();
            OperateLogConverter operateLogConverter = getConverter(converterClass);
            OperateLog operateLog = operateLogConverter.convert(joinPoint.getArgs()[0]);
            operateLog.setDesc(recordOperateLog.desc());
            operateLog.setResult(GSON.toJson(result));
            LOGGER.info("insert operate log: {}", GSON.toJson(operateLog));
        });

        return result;
    }

    private OperateLogConverter getConverter(Class<? extends OperateLogConverter> clazz) {
        return CONVERTER_CACHE.getUnchecked(clazz);
    }

}
