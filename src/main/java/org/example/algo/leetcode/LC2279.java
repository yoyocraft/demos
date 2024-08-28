package org.example.algo.leetcode;

import org.example.algo.ModelParser;
import org.example.algo.OjAssertUtil;

import java.util.Arrays;

/**
 * @author yoyocraft
 * @date 2024/08/25
 */
public class LC2279 {
    public static void main(String[] args) {
        LC2279 lc2279 = new LC2279();
        OjAssertUtil.judgeResult((tc) -> {
            String[] inOut = tc.split(" ");
            int[] capacity = ModelParser.parseIntArray(inOut[0]);
            int[] rocks = ModelParser.parseIntArray(inOut[1]);
            int additionalRocks = Integer.parseInt(inOut[2]);
            int expect = Integer.parseInt(inOut[3]);
            int actual = lc2279.maximumBags(capacity, rocks, additionalRocks);
            OjAssertUtil.assertEquals(expect, actual);
        }, "lc2279");
    }

    public int maximumBags(int[] capacity, int[] rocks, int additionalRocks) {
        int n = capacity.length;
        int[] diff = new int[n];
        for (int i = 0; i < n; i++) {
            diff[i] = capacity[i] - rocks[i];
        }

        Arrays.sort(diff);
        int cnt = 0;
        for (int x : diff) {
            if (additionalRocks >= x) {
                additionalRocks -= x;
                cnt++;
            } else {
                break;
            }
        }
        return cnt;
    }
}
