package org.example.algo.leetcode;

import org.example.algo.OjAssertUtil;

/**
 * @author yoyocraft
 * @date 2024/08/24
 */
public class LC283 {
    public static void main(String[] args) {
        LC283 lc283 = new LC283();
        OjAssertUtil.assertResultWithStream((tcs) -> {
            tcs.forEach(tc -> {
                String[] inOut = tc.split(" ");
                String inArr = inOut[0];
                int[] nums = OjAssertUtil.parseIntArray(inArr);
                lc283.moveZeroes(nums);
                OjAssertUtil.assertEquals(inOut[1], OjAssertUtil.parseString(nums));
            });
        }, "lc283");
    }

    public void moveZeroes(int[] nums) {
        if (nums == null || nums.length == 0) {
            return;
        }

        int n = nums.length, r = 0, l = 0;
        while (r < n) {
            if (nums[r] != 0) {
                nums[l++] = nums[r];
            }
            r++;
        }
        while (l < n) {
            nums[l++] = 0;
        }
    }
}
