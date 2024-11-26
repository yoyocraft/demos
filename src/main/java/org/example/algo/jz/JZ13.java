package org.example.algo.jz;

import org.example.algo.ModelParser;
import org.example.algo.OjAssertUtil;
import org.example.algo.TargetType;
import org.example.algo.constant.SymbolConstant;

import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Deque;

/**
 * <a href="https://www.nowcoder.com/practice/1624bc35a45c42c0bc17d17fa0cba788">JZ13</a>
 * <p>
 * 给定一个长度为 n 的数组 num 和滑动窗口的大小 size ，找出所有滑动窗口里数值的最大值。
 * 窗口大于数组长度或窗口长度为0的时候，返回空。
 * 数据范围：1 <= n <= 10000, 0 <= size <= 10000, 数组中每个元素的值满足 |val| <= 10000
 * 要求：时间复杂度 O(n), 空间复杂度 O(n)
 */
public class JZ13 {

    public static void main(String[] args) {
        JZ13 jz13 = new JZ13();
        OjAssertUtil.judgeResult((tc) -> {
            String[] inOut = tc.split(SymbolConstant.SPACE);
            int[] nums = ModelParser.parseIntArray(inOut[0]);
            int size = Integer.parseInt(inOut[1]);
            String expect = inOut[2];
            ArrayList<Integer> actual = jz13.maxInWindows(nums, size);
            OjAssertUtil.assertEquals(expect, ModelParser.parseString(actual));
        }, "jz13", TargetType.JZ);
    }

    public ArrayList<Integer> maxInWindows(int[] nums, int size) {
        if (size == 0 || nums == null || nums.length == 0) {
            return new ArrayList<>(0);
        }
        ArrayList<Integer> ans = new ArrayList<>();
        Deque<Integer> que = new ArrayDeque<>();
        for (int i = 0; i < nums.length; i++) {
            // 1. 尾部入队列，保证队列单调递减
            while (!que.isEmpty() && nums[i] >= nums[que.getLast()]) {
                que.pollLast();
            }
            que.addLast(i);

            // 2. 出队列，窗口之外的元素移出队列
            if (i - que.getFirst() >= size) {
                que.pollFirst();
            }

            // 3. 记录答案
            if (i >= size - 1) {
                ans.add(nums[que.getFirst()]);
            }
        }
        return ans;
    }
}
