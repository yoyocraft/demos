package org.example.algo.jz;

import org.example.algo.ModelParser;
import org.example.algo.OjAssertUtil;
import org.example.algo.TargetType;
import org.example.algo.constant.SymbolConstant;

import java.util.ArrayList;
import java.util.List;
import java.util.Stack;

/**
 * <a href="https://www.nowcoder.com/practice/54275ddae22f475981afa2244dd448c6">JZ8</a>
 * <p>
 * 用两个栈来实现一个队列，使用n个元素来完成 n 次在队列尾部插入整数(push)和n次在队列头部删除整数(pop)的功能。
 * 队列中的元素为int类型。保证操作合法，即保证pop操作时队列内已有元素。
 * 数据范围：n <= 1000
 * 要求：存储n个元素的空间复杂度为 O(n)， 插入和删除的时间复杂度为 O(1)
 */
public class JZ8 {

    public static void main(String[] args) {
        JZ8 jz8 = new JZ8();
        OjAssertUtil.judgeResult((tc) -> {
            String[] inOut = tc.split(SymbolConstant.SPACE);
            List<String> inCommands = ModelParser.parseList(inOut[0], String.class);
            List<Integer> actual = new ArrayList<>();
            inCommands.forEach(cmd -> {
                if ("POP".equals(cmd)) {
                    actual.add(jz8.pop());
                } else {
                    int node = Integer.parseInt(cmd.split(SymbolConstant.UNDER_LINE)[1]);
                    jz8.push(node);
                }
            });
            OjAssertUtil.assertEquals(inOut[1], ModelParser.parseString(actual));
        }, "jz8", TargetType.JZ);
    }

    Stack<Integer> stack1 = new Stack<>();
    Stack<Integer> stack2 = new Stack<>();

    public void push(int node) {
        stack1.push(node);
    }

    public int pop() {
        in2Out();
        return stack2.pop();
    }

    void in2Out() {
        if (!stack2.isEmpty()) {
            return;
        }
        // stack2 为空，表明之前的数据均已出栈
        while (!stack1.isEmpty()) {
            stack2.push(stack1.pop());
        }
    }
}
