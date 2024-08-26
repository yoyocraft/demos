package org.example.algo.leetcode;

import org.example.algo.OjAssertUtil;

/**
 * @author yoyocraft
 * @date 2024/08/25
 */
public class LC55 {
    public static void main(String[] args) {
        LC55 lc55 = new LC55();
        OjAssertUtil.judgeResult((tc) -> {
            String[] inOut = tc.split(" ");
            int[] nums = OjAssertUtil.parseIntArray(inOut[0]);
            boolean expect = Boolean.parseBoolean(inOut[1]);
            boolean actual = lc55.canJump(nums);
            OjAssertUtil.assertEquals(expect, actual);
        }, "lc55");
    }

    public boolean canJump(int[] nums) {
        int n = nums.length;
        int rightMost = 0;
        for (int i = 0; i <= rightMost; i++) {
            rightMost = Math.max(rightMost, i + nums[i]);
            if (rightMost >= n - 1) {
                return true;
            }
        }
        return false;
    }
}
