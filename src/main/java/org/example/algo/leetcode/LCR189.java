package org.example.algo.leetcode;

import org.example.algo.OjAssertUtil;
import org.example.algo.constant.SymbolConstant;

/**
 * @author yoyocraft
 * @date 2024/09/05
 */
public class LCR189 {
    public static void main(String[] args) {
        LCR189 lcr189 = new LCR189();
        OjAssertUtil.judgeResult((tc) -> {
            String[] inOut = tc.split(SymbolConstant.SPACE);
            int target = Integer.parseInt(inOut[0]);
            int expect = Integer.parseInt(inOut[1]);
            int actual = lcr189.mechanicalAccumulator(target);
            OjAssertUtil.assertEquals(expect, actual);
        }, "lcr189");
    }

    public int mechanicalAccumulator(int target) {
        boolean ignore = target > 1 && (target += mechanicalAccumulator(target - 1)) > 0;
        return target;
    }
}
