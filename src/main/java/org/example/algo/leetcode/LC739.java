package org.example.algo.leetcode;

import org.example.algo.ModelParser;
import org.example.algo.OjAssertUtil;

import java.util.ArrayDeque;
import java.util.Arrays;
import java.util.Deque;
import java.util.stream.Collectors;

/**
 * @author yoyocraft
 * @date 2024/08/22
 */
public class LC739 {

    public static void main(String[] args) {
        LC739 lc739 = new LC739();
        OjAssertUtil.judgeResult(tc -> {
            String[] inOut = tc.split(" ");
            String in = inOut[0], expect = inOut[1];
            int[] temperatures = ModelParser.parseIntArray(in);
            int[] ans = lc739.dailyTemperatures(temperatures);
            String actual = ModelParser.parseString(ans);
            OjAssertUtil.assertEquals(expect, actual);
        }, "lc739");
    }

    public int[] dailyTemperatures(int[] temperatures) {
        int n = temperatures.length;
        if (n <= 1) {
            return new int[]{0};
        }
        int[] ans = new int[n];
        // 单调递减栈
        Deque<Integer> st = new ArrayDeque<>(n);
        for (int i = 0; i < n; i++) {
            while (!st.isEmpty() && temperatures[i] > temperatures[st.peek()]) {
                int topIdx = st.pop();
                ans[topIdx] = i - topIdx;
            }
            st.push(i);
        }
        return ans;
    }
}
