package org.example.algo.leetcode;

import org.example.algo.OjAssertUtil;

/**
 * @author yoyocraft
 * @date 2024/08/23
 */
public class LC1143 {

    public static void main(String[] args) {
        LC1143 lc1143 = new LC1143();
        OjAssertUtil.judgeResultWithStream((tcs) -> {
            tcs.forEach(tc -> {
                String[] inOut = tc.split(" ");
                String text1 = inOut[0], text2 = inOut[1];
                int expect = Integer.parseInt(inOut[2]);
                OjAssertUtil.assertEquals(expect, lc1143.longestCommonSubsequence(text1, text2));
            });
        }, "lc1143");
    }
    public int longestCommonSubsequence(String text1, String text2) {
        int m = text1.length(), n = text2.length();
        if (m == 0 || n == 0) {
            return 0;
        }
        char[] s = text1.toCharArray(), t = text2.toCharArray();
        int[][] f = new int[m + 1][n + 1];
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                if (s[i] == t[j]) {
                    f[i + 1][j + 1] = f[i][j] + 1;
                } else {
                    f[i + 1][j + 1] = Math.max(f[i + 1][j], f[i][j + 1]);
                }
            }
        }
        return f[m][n];
    }
}
