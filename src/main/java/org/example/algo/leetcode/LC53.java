package org.example.algo.leetcode;

import org.example.algo.ModelParser;
import org.example.algo.OjAssertUtil;
import org.example.algo.constant.SymbolConstant;

/**
 * @author yoyocraft
 * @date 2024/09/04
 */
public class LC53 {

    public static void main(String[] args) {
        LC53 lc53 = new LC53();
        OjAssertUtil.judgeResult((tc) -> {
            String[] inOut = tc.split(SymbolConstant.SPACE);
            int[] nums = ModelParser.parseIntArray(inOut[0]);
            int expect = Integer.parseInt(inOut[1]);
            int actual = lc53.maxSubArray(nums);
            OjAssertUtil.assertEquals(expect, actual);
        }, "lc53");
    }

    public int maxSubArray(int[] nums) {
        if (nums == null || nums.length == 0) {
            return 0;
        }
        int ans = Integer.MIN_VALUE;
        // 最小前缀和
        int minPreSum = 0;
        int preSum = 0;
        for (int x : nums) {
            preSum += x;
            ans = Math.max(ans, preSum - minPreSum);
            minPreSum = Math.min(minPreSum, preSum);
        }
        return ans;
    }

    private int f1(int[] nums) {
        if (nums == null || nums.length == 0) {
            return 0;
        }
        int n = nums.length;
        int f0 = nums[0], f1, ans = f0;
        for (int i = 1; i < n; i++) {
            // f[i] = max(f[i-1], 0) + nums[i]
            f1 = Math.max(f0, 0) + nums[i];
            ans = Math.max(ans, f1);
            f0 = f1;
        }
        return ans;
    }

}
