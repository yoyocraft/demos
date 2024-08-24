package org.example.proxy;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;

/**
 * @author yoyocraft
 * @date 2024/08/13
 */
public class ProxyProject implements InvocationHandler {
    private final Object target;

    public ProxyProject(Object target) {
        this.target = target;
    }

    @Override
    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
        System.out.println("pre invoke");
        return method.invoke(target, args);
    }
}
