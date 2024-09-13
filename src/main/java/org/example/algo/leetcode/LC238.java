package org.example.algo.leetcode;

import org.example.algo.ModelParser;
import org.example.algo.OjAssertUtil;
import org.example.algo.constant.SymbolConstant;

import java.util.Arrays;

/**
 * @author yoyocraft
 * @date 2024/09/13
 */
public class LC238 {

    public static void main(String[] args) {
        LC238 lc238 = new LC238();
        OjAssertUtil.judgeResult((tc) -> {
            String[] inOut = tc.split(SymbolConstant.SPACE);
            int[] nums = ModelParser.parseIntArray(inOut[0]);
            String expect = inOut[1];
            int[] actual = lc238.productExceptSelf(nums);
            OjAssertUtil.assertEquals(expect, ModelParser.parseString(actual));
        }, "lc238");
    }

    public int[] productExceptSelf(int[] nums) {
        if(nums == null || nums.length == 0) {
            return new int[0];
        }

        int n = nums.length;
        int[] f = new int[n];
        Arrays.fill(f, 1);
        for(int i = 1; i < n; i++) {
            f[i] = f[i - 1] * nums[i - 1];
        }

        int r = 1;
        for(int i = n - 1; i >= 0; i--) {
            f[i] *= r;
            r *= nums[i];
        }
        return f;
    }
}
