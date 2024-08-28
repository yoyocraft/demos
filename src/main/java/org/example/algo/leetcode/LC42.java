package org.example.algo.leetcode;

import org.example.algo.ModelParser;
import org.example.algo.OjAssertUtil;

import java.util.ArrayDeque;
import java.util.Deque;

/**
 * @author yoyocraft
 * @date 2024/08/26
 */
public class LC42 {

    public static void main(String[] args) {
        LC42 lc42 = new LC42();
        OjAssertUtil.judgeResult((tc) -> {
            String[] inOut = tc.split(" ");
            int[] height = ModelParser.parseIntArray(inOut[0]);
            int expect = Integer.parseInt(inOut[1]);
            int actual = lc42.trap(height);
            OjAssertUtil.assertEquals(expect, actual);
        }, "lc42");
    }

    public int trap(int[] height) {
        int ans = 0;
        Deque<Integer> st = new ArrayDeque<>();
        for (int i = 0; i < height.length; i++) {
            while (!st.isEmpty() && height[i] >= height[st.peek()]) {
                int bottomH = height[st.pop()];
                if (st.isEmpty()) {
                    break;
                }
                int l = st.peek();
                int dh = Math.min(height[i], height[l]) - bottomH;
                ans += dh * (i - l - 1);
            }
            st.push(i);
        }
        return ans;
    }

    public int trap2(int[] height) {
        int ans = 0;
        int l = 0, r = height.length - 1;
        // 前后缀最大值
        int preMax = 0, sufMax = 0;
        while (l < r) {
            preMax = Math.max(preMax, height[l]);
            sufMax = Math.max(sufMax, height[r]);
            ans += preMax < sufMax ? preMax - height[l++] : sufMax - height[r--];
        }
        return ans;
    }

    public int trap1(int[] height) {
        int n = height.length;
        // preMax[i] 表示从 height[0] ~ height[i] 的最大值
        int[] preMax = new int[n];
        preMax[0] = height[0];
        for (int i = 1; i < n; i++) {
            preMax[i] = Math.max(preMax[i - 1], height[i]);
        }

        // sufMax[i] 表示从 height[i] ~ height[n-1] 的最大值
        int[] sufMax = new int[n];
        sufMax[n - 1] = height[n - 1];
        for (int i = n - 2; i >= 0; i--) {
            sufMax[i] = Math.max(sufMax[i + 1], height[i]);
        }

        int ans = 0;
        for (int i = 0; i < n; i++) {
            // 每一格都是一个宽为1的桶
            ans += Math.min(preMax[i], sufMax[i]) - height[i];
        }
        return ans;
    }
}
