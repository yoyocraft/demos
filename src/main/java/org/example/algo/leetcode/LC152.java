package org.example.algo.leetcode;

import org.example.algo.ModelParser;
import org.example.algo.OjAssertUtil;
import org.example.algo.constant.SymbolConstant;

public class LC152 {

    public static void main(String[] args) {
        LC152 lc152 = new LC152();
        OjAssertUtil.judgeResult((tc) -> {
            String[] inOut = tc.split(SymbolConstant.SPACE);
            int[] nums = ModelParser.parseIntArray(inOut[0]);
            int expect = Integer.parseInt(inOut[1]);
            int actual = lc152.maxProduct(nums);
            OjAssertUtil.assertEquals(expect, actual);
        }, "lc152");
    }

    public int maxProduct(int[] nums) {
        int mn = 1, mx = 1;
        int ans = nums[0];
        for (int x : nums) {
            if (x < 0) {
                // 遇到负数，交换 mn 和 mx
                // 保证 mx 一直是最大值
                mn ^= mx;
                mx ^= mn;
                mn ^= mx;
            }

            ans = Math.max(ans, mx * x);
            mx = Math.max(mx * x, x);
            mn = Math.min(mn * x, x);
        }
        return ans;
    }
}
