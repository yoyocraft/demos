package org.example.algo.leetcode;

import org.example.algo.OjAssertUtil;
import org.example.algo.constant.SymbolConstant;

/**
 * @author yoyocraft
 * @date 2024/09/15
 */
public class LC32 {
    public static void main(String[] args) {
        LC32 lc32 = new LC32();
        OjAssertUtil.judgeResult((tc) -> {
            String[] inOut = tc.split(SymbolConstant.SPACE);
            String s = inOut[0];
            int expect = Integer.parseInt(inOut[1]);
            int actual = lc32.longestValidParentheses(s);
            OjAssertUtil.assertEquals(expect, actual);
        }, "lc32");
    }

    public int longestValidParentheses(String s) {
        int n = s.length();
        if (n < 2) {
            return 0;
        }

        int[] f = new int[n];
        int ans = 0;
        for (int i = 1; i < n; i++) {
            if (s.charAt(i) == '(') {
                continue;
            }

            if (s.charAt(i - 1) == '(') {
                // '()'
                f[i] = 2;
                if (i - 2 >= 0) {
                    f[i] += f[i - 2];
                }
            } else if (f[i - 1] > 0) {
                // '((..))'
                int lastIdx = i - f[i - 1] - 1;
                if (lastIdx >= 0 && s.charAt(lastIdx) == '(') {
                    f[i] = f[i - 1] + 2;
                    if (lastIdx - 1 >= 0) {
                        // (..)((..))
                        f[i] += f[lastIdx - 1];
                    }
                }
            }
            ans = Math.max(ans, f[i]);
        }
        return ans;
    }
}
