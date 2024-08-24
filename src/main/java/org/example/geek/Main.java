package org.example.geek;

/**
 * @author yoyocraft
 * @date 2024/08/10
 */
public class Main {

    public static void main(String[] args) {
        LogTraceIdGenerator logTraceIdGenerator = new RandomIdGenerator();
        System.out.println(logTraceIdGenerator.generate());
    }

}
