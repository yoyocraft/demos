package org.example.proxy;

import java.lang.reflect.Proxy;

/**
 * @author yoyocraft
 * @date 2024/08/13
 */
public class Main {
    public static void main(String[] args) {
        IDemo demo = new RealProject();
        Object proxy = Proxy.newProxyInstance(
                Main.class.getClassLoader(),
                new Class[]{IDemo.class},
                new ProxyProject(demo)
        );
        System.out.println(((IDemo) proxy).sayHello());
    }
}
