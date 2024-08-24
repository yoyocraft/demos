package org.example.proxy;

/**
 * @author yoyocraft
 * @date 2024/08/13
 */
public class RealProject implements IDemo {
    @Override
    public String sayHello() {
        return "Hello, this is RealProject!";
    }
}
