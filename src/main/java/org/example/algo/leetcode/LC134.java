package org.example.algo.leetcode;

import org.example.algo.ModelParser;
import org.example.algo.OjAssertUtil;
import org.example.algo.constant.SymbolConstant;

/**
 * @author yoyocraft
 * @date 2024/10/06
 */
public class LC134 {

    public static void main(String[] args) {
        LC134 lc134 = new LC134();
        OjAssertUtil.judgeResult((tc) -> {
            String[] inOut = tc.split(SymbolConstant.SPACE);
            int[] gas = ModelParser.parseIntArray(inOut[0]);
            int[] cost = ModelParser.parseIntArray(inOut[1]);
            int expect = Integer.parseInt(inOut[2]);
            int actual = lc134.canCompleteCircuit(gas, cost);
            OjAssertUtil.assertEquals(expect, actual);
        }, "lc134");
    }

    public int canCompleteCircuit(int[] gas, int[] cost) {
        if (gas.length == 0 || cost.length == 0) {
            return -1;
        }
        int ans = 0;
        // 最小油量
        int minS = 0;
        int s = 0;
        for (int i = 0; i < gas.length; i++) {
            s += gas[i] - cost[i];
            if (s < minS) {
                minS = s;
                ans = i + 1;
            }
        }
        return s < 0 ? -1 : ans;
    }
}
