package org.example.algo.leetcode;

import org.example.algo.ModelParser;
import org.example.algo.OjAssertUtil;

/**
 * @author yoyocraft
 * @date 2024/08/25
 */
public class LC45 {
    public static void main(String[] args) {
        LC45 lc45 = new LC45();
        OjAssertUtil.judgeResult((tc) -> {
            String[] inOut = tc.split(" ");
            int[] nums = ModelParser.parseIntArray(inOut[0]);
            int expect = Integer.parseInt(inOut[1]);
            int actual = lc45.jump(nums);
            OjAssertUtil.assertEquals(expect, actual);
        }, "lc45");
    }

    public int jump(int[] nums) {
        int n = nums.length;
        int curCover = 0, nxtCover = 0;
        int step = 0;
        // [0, n-2]
        for (int i = 0; i < n - 1; i++) {
            nxtCover = Math.max(nxtCover, i + nums[i]);
            // 当 i == n-2 时，要么能够覆盖到 n-1 ，要么 nxtCover >= n-1
            if (i == curCover) {
                step++;
                curCover = nxtCover;
            }
        }
        return step;
    }
}
