package org.example.algo.leetcode;

import org.example.algo.ModelParser;
import org.example.algo.OjAssertUtil;
import org.example.algo.constant.SymbolConstant;

/**
 * @author yoyocraft
 * @date 2024/09/05
 */
public class LC75 {

    public static void main(String[] args) {
        LC75 lc75 = new LC75();
        OjAssertUtil.judgeResult((tc) -> {
            String[] inOut = tc.split(SymbolConstant.SPACE);
            int[] nums = ModelParser.parseIntArray(inOut[0]);
            String expect = inOut[1];
            lc75.sortColors(nums);
            OjAssertUtil.assertEquals(expect, ModelParser.parseString(nums));
        }, "lc75");
    }

    public void sortColors(int[] nums) {
        if (nums == null || nums.length == 0) {
            return;
        }
        int n = nums.length;

        int l = 0, r = n - 1;
        for (int i = 0; i <= r; ) {
            int x = nums[i];
            if (x == 0) {
                swap(nums, l++, i++);
            } else if (x == 2) {
                swap(nums, r--, i);
            } else {
                // x == 1
                i++;
            }
        }
    }

    void swap(int[] nums, int i, int j) {
        int tmp = nums[i];
        nums[i] = nums[j];
        nums[j] = tmp;
    }
}
