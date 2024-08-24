package org.example.algo.leetcode;

import org.example.algo.OjAssertUtil;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

/**
 * @author yoyocraft
 * @date 2024/08/23
 */
public class LC560 {

    public static void main(String[] args) {
        LC560 lc560 = new LC560();
        OjAssertUtil.assertResultWithStream((tcs) -> {
            tcs.forEach(tc -> {
                String[] inOut = tc.split(" ");
                String arrStr = inOut[0];
                int[] nums = Arrays.stream(arrStr.substring(1, arrStr.length() - 1).split(","))
                        .mapToInt(Integer::parseInt)
                        .toArray();
                int aim = Integer.parseInt(inOut[1]);
                int expect = Integer.parseInt(inOut[2]);
                OjAssertUtil.assertEquals(expect, lc560.subarraySum(nums, aim));
            });
        }, "lc560");
    }

    public int subarraySum(int[] nums, int aim) {
        int n = nums.length;
        if (n == 0) {
            return 0;
        }
        if (n == 1) {
            return nums[0] == aim ? 1 : 0;
        }
        Map<Long, Integer> preSumMap = new HashMap<>();
        preSumMap.put(0L, 1);
        long s = 0L;
        int ans = 0;
        for (int x : nums) {
            s += x;
            ans += preSumMap.getOrDefault(s - aim, 0);
            preSumMap.put(s, preSumMap.getOrDefault(s, 0) + 1);
        }
        return ans;
    }
}
