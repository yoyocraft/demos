package org.example.algo.util;

/**
 * @author yoyocraft
 * @date 2024/09/09
 */
public class AlgoUtil {

    /**
     * lowerBound 返回最小的满足 nums[i] >= target 的 i
     * 如果数组为空，或者所有数都 < target，则返回 nums.length
     * 要求 nums 是非递减的，即 nums[i] <= nums[i + 1]
     */
    public static int lowerBound(int[] nums, int target) {
        // [left, right]
        int left = 0, right = nums.length - 1;
        while (left <= right) {
            // 循环不变量：
            // nums[left - 1] < target
            // nums[right + 1] >= target
            int mid = left + (right - left) / 2;
            if (nums[mid] < target) {
                // [mid+1, right]
                left = mid + 1;
            } else {
                // [left, mid-1]
                right = mid - 1;
            }
        }
        return left;
    }

    public static int lowerBoundV2(int[] nums, int target) {
        // [left, right)
        int left = 0, right = nums.length;
        while (left < right) {
            // 循环不变量：
            // nums[left - 1] < target
            // nums[right] >= target
            int mid = left + (right - left) / 2;
            if (nums[mid] < target) {
                // [mid+1, right)
                left = mid + 1;
            } else {
                // [left, mid)
                right = mid;
            }
        }
        // or return right
        return left;
    }

    public static int lowerBoundV3(int[] nums, int target) {
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
