package org.example.algo.leetcode;

import org.example.algo.OjAssertUtil;

import java.util.Arrays;

/**
 * @author yoyocraft
 * @date 2024/08/25
 */
public class LC698 {

    public static void main(String[] args) {
        LC698 lc698 = new LC698();
        OjAssertUtil.judgeResult((tc) -> {
            String[] inOut = tc.split(" ");
            int[] nums = OjAssertUtil.parseIntArray(inOut[0]);
            int k = Integer.parseInt(inOut[1]);
            boolean expect = Boolean.parseBoolean(inOut[2]);
            boolean actual = lc698.canPartitionKSubsets(nums, k);
            OjAssertUtil.assertEquals(expect, actual);
        }, "lc698");
    }

    public boolean canPartitionKSubsets(int[] nums, int k) {
        int n = nums.length;
        int s = 0;
        for (int x : nums) {
            s += x;
        }

        if (s % k != 0) {
            return false;
        }

        int target = s / k;

        // 降序排序
        Arrays.sort(nums);
        for (int l = 0, r = n - 1; l < r; l++, r--) {
            int tmp = nums[l];
            nums[l] = nums[r];
            nums[r] = tmp;
        }

        return f(nums, 0, new int[k], k, target);
    }

    boolean f(int[] nums, int i, int[] bucket, int k, int target) {
        // 结束，因为在过程中已经判断了是否合法，所以直接返回 true
        if (i == nums.length) {
            return true;
        }

        for (int j = 0; j < k; j++) {
            // 当前桶和上一个桶内的元素和相等，跳过
            if (j > 0 && bucket[j] == bucket[j - 1]) {
                continue;
            }
            // 放入球后超过 target 的值，跳过
            if (bucket[j] + nums[i] > target) {
                continue;
            }

            bucket[j] += nums[i];
            if (f(nums, i + 1, bucket, k, target)) {
                // System.out.printf("bucket[%s] put a ball %s\n", j, nums[i]);
                return true;
            }
            bucket[j] -= nums[i];
        }
        return false;
    }
}
