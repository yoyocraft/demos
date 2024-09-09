package org.example.algo.leetcode;

import org.example.algo.ModelParser;
import org.example.algo.OjAssertUtil;
import org.example.algo.constant.SymbolConstant;

/**
 * @author yoyocraft
 * @date 2024/09/09
 */
public class LC34 {
    public static void main(String[] args) {
        LC34 lc34 = new LC34();
        OjAssertUtil.judgeResult((tc) -> {
            String[] inOut = tc.split(SymbolConstant.SPACE);
            int[] nums = ModelParser.parseIntArray(inOut[0]);
            int target = Integer.parseInt(inOut[1]);
            String expect = inOut[2];
            int[] actual = lc34.searchRange(nums, target);
            OjAssertUtil.assertEquals(expect, ModelParser.parseString(actual));
        }, "lc34");
    }

    public int[] searchRange(int[] nums, int target) {
        if (nums == null || nums.length == 0) {
            return new int[]{-1, -1};
        }

        // 区间开始位置 >=target
        int startIdx = lowerBound(nums, target);
        // 数组中不存在 target
        if (startIdx == nums.length || nums[startIdx] != target) {
            return new int[]{-1, -1};
        }
        // startIdx 存在，endIdx 一定存在
        // 区间结束位置 <=target => (>target)-1 => (>=(target+1))-1
        int endIdx = lowerBound(nums, target + 1) - 1;
        return new int[]{startIdx, endIdx};
    }

    /**
     * lowerBound 返回最小的满足 nums[i] >= target 的 i
     * 如果数组为空，或者所有数都 < target，则返回 nums.length
     * 要求 nums 是非递减的，即 nums[i] <= nums[i + 1]
     */
    int lowerBound(int[] nums, int target) {
        // (left, right)
        int left = -1, right = nums.length;
        while (left + 1 < right) {
            // 循环不变量：
            // nums[left] < target
            // nums[right] >= target
            int mid = left + (right - left) / 2;
            if (nums[mid] < target) {
                // (mid, right)
                left = mid;
            } else {
                // (left, mid)
                right = mid;
            }
        }
        return right;
    }
}
