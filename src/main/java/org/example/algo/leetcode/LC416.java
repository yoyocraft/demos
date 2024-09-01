package org.example.algo.leetcode;

import org.example.algo.ModelParser;
import org.example.algo.OjAssertUtil;
import org.example.algo.constant.SymbolConstant;

/**
 * @author yoyocraft
 * @date 2024/09/01
 */
public class LC416 {

    public static void main(String[] args) {
        LC416 lc416 = new LC416();
        OjAssertUtil.judgeResult((tc) -> {
            String[] inOut = tc.split(SymbolConstant.SPACE);
            int[] nums = ModelParser.parseIntArray(inOut[0]);
            boolean expect = Boolean.parseBoolean(inOut[1]);
            boolean actual = lc416.canPartition(nums);
            OjAssertUtil.assertEquals(expect, actual);
        }, "lc416");
    }

    public boolean canPartition(int[] nums) {
        int s = 0;
        for (int x : nums) {
            s += x;
        }

        // s 必须是偶数
        if ((s & 1) == 1) {
            return false;
        }

        // 01背包
        int tar = s >>> 1;
        int[] f = new int[tar + 1];
        for (int x : nums) {
            for (int j = tar; j >= x; j--) {
                f[j] = Math.max(f[j], f[j - x] + x);
            }
        }
        // 放满背包的条件是 f[tar] == tar
        return f[tar] == tar;
    }
}
