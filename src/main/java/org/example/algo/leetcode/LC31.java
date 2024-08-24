package org.example.algo.leetcode;

import org.example.algo.OjAssertUtil;

import java.util.Arrays;

/**
 * @author yoyocraft
 * @date 2024/08/21
 */
public class LC31 {

    public static void main(String[] args) {
        LC31 lc31 = new LC31();
        OjAssertUtil.assertResult((tc) -> {
            String[] inOut = tc.split(" ");
            String inStr = inOut[0];
            int[] nums = Arrays.stream(inStr.split(""))
                    .mapToInt(Integer::parseInt)
                    .toArray();
            lc31.nextPermutation(nums);
            String actual = Arrays.stream(nums)
                    .mapToObj(String::valueOf)
                    .reduce((a, b) -> a + b)
                    .orElse("");
            OjAssertUtil.assertEquals(inOut[1], actual);
        }, "lc31");
    }

    public void nextPermutation(int[] nums) {
        int n = nums.length;
        if (n == 0) {
            return;
        }

        // 1. 从右至左找到第一个升序的序列 [mn, mn+1]
        int mn = -1;
        for (int i = n - 2; i >= 0; i--) {
            if (nums[i] < nums[i + 1]) {
                mn = i;
                break;
            }
        }

        // 2. 如果存在升序对，倒序找到第一个比 nums[mn] 大的数，与之交换
        if (mn != -1) {
            for (int i = n - 1; i > mn; i--) {
                if (nums[i] > nums[mn]) {
                    swap(nums, i, mn);
                    break;
                }
            }
        }

        // 3. 此时 [mn+1, n-1] 是降序的，逆序处理 [mn+1, n-1]
        for (int i = mn + 1, j = n - 1; i < j; i++, j--) {
            swap(nums, i, j);
        }
    }

    void swap(int[] nums, int i, int j) {
        int t = nums[i];
        nums[i] = nums[j];
        nums[j] = t;
    }
}
