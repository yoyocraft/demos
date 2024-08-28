package org.example.algo.leetcode;

import org.example.algo.ModelParser;
import org.example.algo.OjAssertUtil;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * @author yoyocraft
 * @date 2024/08/26
 */
public class LC15 {

    public static void main(String[] args) {
        LC15 lc15 = new LC15();
        OjAssertUtil.judgeResult((tc) -> {
            String[] inOut = tc.split(" ");
            int[] nums = ModelParser.parseIntArray(inOut[0]);
            String expect = inOut[1];
            List<List<Integer>> actual = lc15.threeSum(nums);
            String actualStr = ModelParser.parseString(actual);
            OjAssertUtil.assertEquals(expect, actualStr);
        }, "lc15");
    }

    public List<List<Integer>> threeSum(int[] nums) {
        int n = nums.length;
        List<List<Integer>> ans = new ArrayList<>();
        if (n == 0) {
            return ans;
        }

        Arrays.sort(nums);

        for (int i = 0; i < n - 2; i++) {
            int x = nums[i];
            // 去重
            if (i > 0 && x == nums[i - 1]) {
                continue;
            }

            // 优化1
            if (x + nums[i + 1] + nums[i + 2] > 0) {
                break;
            }

            // 优化2
            if (x + nums[n - 2] + nums[n - 1] < 0) {
                continue;
            }

            // 二分查找
            int j = i + 1, k = n - 1;
            while (j < k) {
                int s = x + nums[j] + nums[k];
                if (s == 0) {
                    List<Integer> list = new ArrayList<>();
                    list.add(x);
                    list.add(nums[j]);
                    list.add(nums[k]);
                    ans.add(list);
                    // 去重
                    j++;
                    while (j < k && nums[j] == nums[j - 1]) {
                        j++;
                    }
                    k--;
                    while (j < k && nums[k] == nums[k + 1]) {
                        k--;
                    }
                } else if (s > 0) {
                    k--;
                } else {
                    j++;
                }
            }
        }
        return ans;
    }
}
