package org.example.algo.leetcode;

import org.example.algo.ModelParser;
import org.example.algo.OjAssertUtil;
import org.example.algo.constant.SymbolConstant;

/**
 * @author yoyocraft
 * @date 2024/09/21
 */
public class LC209 {

    public static void main(String[] args) {
        LC209 lc209 = new LC209();
        OjAssertUtil.judgeResult((tc) -> {
            String[] inOut = tc.split(SymbolConstant.SPACE);
            int target = Integer.parseInt(inOut[0]);
            int[] nums = ModelParser.parseIntArray(inOut[1]);
            int expect = Integer.parseInt(inOut[2]);
            int actual = lc209.minSubArrayLen(target, nums);
            OjAssertUtil.assertEquals(expect, actual);
        }, "lc209");
    }

    public int minSubArrayLen(int target, int[] nums) {
        if (nums == null || nums.length == 0) {
            return 0;
        }
        int n = nums.length;
        int ans = n + 1;
        for (int l = 0, r = 0, s = 0; r < n; r++) {
            s += nums[r];
            // 缩小窗口，记录答案
            while (s >= target) {
                ans = Math.min(ans, r - l + 1);
                s -= nums[l++];
            }
        }
        return ans <= n ? ans : 0;
    }
}
