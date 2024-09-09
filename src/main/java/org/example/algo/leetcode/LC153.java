package org.example.algo.leetcode;

import org.example.algo.ModelParser;
import org.example.algo.OjAssertUtil;
import org.example.algo.constant.SymbolConstant;

/**
 * @author yoyocraft
 * @date 2024/09/09
 */
public class LC153 {

    public static void main(String[] args) {
        LC153 lc153 = new LC153();
        OjAssertUtil.judgeResult((tc) -> {
            String[] inOut = tc.split(SymbolConstant.SPACE);
            int[] nums = ModelParser.parseIntArray(inOut[0]);
            int expect = Integer.parseInt(inOut[1]);
            int actual = lc153.findMin(nums);
            OjAssertUtil.assertEquals(expect, actual);
        }, "lc153");
    }

    public int findMin(int[] nums) {
        int n = nums.length;
        if (n == 1) {
            return nums[0];
        }

        // nums[n-1] 要么是最小值，要么在最小值右侧
        int l = -1, r = n - 1;
        while (l + 1 < r) {
            int mid = l + (r - l) / 2;
            if (nums[mid] > nums[n - 1]) {
                l = mid;
            } else {
                r = mid;
            }
        }
        return nums[r];
    }
}
