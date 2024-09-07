package org.example.demo;

/**
 * @author yoyocraft
 * @date 2024/09/06
 */
public class GetFieldTest {
    private final char[] values = new char[]{'a', 'b', 'c'};

    public void testFn() {
        char[] val = this.values;
        System.out.println(val[0]);
        System.out.println(val[1]);
        System.out.println(val[2]);
    }

    public static void main(String[] args) {
        GetFieldTest getFieldTest = new GetFieldTest();
        getFieldTest.testFn();
    }
}
