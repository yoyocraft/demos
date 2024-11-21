package org.example.algo.jz;

import org.example.algo.ModelParser;
import org.example.algo.OjAssertUtil;
import org.example.algo.TargetType;
import org.example.algo.constant.SymbolConstant;

public class JZ10 {

    public static void main(String[] args) {
        JZ10 jz10 = new JZ10();
        OjAssertUtil.judgeResult((tc) -> {
            String[] inOut = tc.split(SymbolConstant.SPACE);
            int[] nums = ModelParser.parseIntArray(inOut[0]);
            int expect = Integer.parseInt(inOut[1]);
            int actual = jz10.minNumberInRotateArray(nums);
            OjAssertUtil.assertEquals(expect, actual);
        }, "jz10", TargetType.JZ);
    }

    public int minNumberInRotateArray(int[] nums) {
        if (nums == null || nums.length == 0) {
            return 0;
        }
        int n = nums.length;
        // (l,r)
        // 每次 mid 位置和最右边的元素比较
        // 如果比最右边的元素大 => 最小值在 (mid,r) 之间
        // 如果比最右边的元素小 => 最小值在 (l,mid) 之间
        int l = -1, r = n - 1;
        while (l + 1 < r) {
            int mid = l + (r - l) / 2;
            if (nums[mid] > nums[r]) {
                l = mid;
            } else if (nums[mid] < nums[r]) {
                r = mid;
            } else {
                r--;
            }
        }
        return nums[r];
    }
}
