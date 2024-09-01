package org.example.algo.leetcode;

import org.example.algo.OjAssertUtil;
import org.example.algo.constant.SymbolConstant;

/**
 * @author yoyocraft
 * @date 2024/09/01
 */
public class LC5 {
    public static void main(String[] args) {
        LC5 lc5 = new LC5();
        OjAssertUtil.judgeResult((tc) -> {
            String[] inOut = tc.split(SymbolConstant.SPACE);
            String expect = inOut[1];
            String actual = lc5.longestPalindrome(inOut[0]);
            OjAssertUtil.assertEquals(expect, actual);
        }, "lc5");
    }

    public String longestPalindrome(String s) {
        if (s == null || s.length() < 2) {
            return s;
        }

        int n = s.length();
        char[] ss = s.toCharArray();
        int[] ans = {0, 0};
        int mxLen = 0;
        for (int i = 0; i < n; i++) {
            int[] sin = spread(ss, i, i);
            int[] dou = spread(ss, i, i + 1);
            int[] max = sin[1] > dou[1] ? sin : dou;
            if (max[1] > mxLen) {
                ans = max;
                mxLen = max[1];
            }
        }
        return s.substring(ans[0], ans[0] + ans[1]);
    }

    /**
     * return [start, len]
     */
    private int[] spread(char[] s, int l, int r) {
        while (l >= 0 && r < s.length && s[l] == s[r]) {
            l--;
            r++;
        }
        return new int[]{l + 1, r - l - 1};
    }

    private String f1(String s) {
        int n = s.length();
        if (n < 2) {
            return s;
        }

        boolean[][] f = new boolean[n][n];
        int l = 0, mxLen = 0;
        char[] ss = s.toCharArray();
        for (int i = n - 1; i >= 0; i--) {
            for (int j = i; j < n; j++) {
                f[i][j] = ss[i] == ss[j] && (j - i < 2 || f[i + 1][j - 1]);
                if (f[i][j] && j - i + 1 > mxLen) {
                    mxLen = j - i + 1;
                    l = i;
                }
            }
        }
        return s.substring(l, l + mxLen);
    }
}
