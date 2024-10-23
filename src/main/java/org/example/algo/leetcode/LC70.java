package org.example.algo.leetcode;

import org.example.algo.OjAssertUtil;
import org.example.algo.constant.SymbolConstant;

public class LC70 {

    public static void main(String[] args) {
        LC70 lc70 = new LC70();
        OjAssertUtil.judgeResult((tc) -> {
            String[] inOut = tc.split(SymbolConstant.SPACE);
            int n = Integer.parseInt(inOut[0]);
            int expect = Integer.parseInt(inOut[1]);
            int actual = lc70.climbStairs(n);
            OjAssertUtil.assertEquals(expect, actual);
        }, "lc70");
    }

    public int climbStairs(int n) {
        if (n < 3) {
            return n;
        }

        long[][] ans = {
                {1, 1},
                {0, 0}
        };

        long[][] base = {
                {1, 1},
                {1, 0}
        };

        int p = n - 1;
        while (p != 0) {
            if ((p & 1) != 0) {
                ans = mul(ans, base);
            }
            base = mul(base, base);
            p >>>= 1;
        }

        return (int) ans[0][0];
    }

    long[][] mul(long[][] a, long[][] b) {
        // a[0].length == b.length
        int r = a.length, c = b[0].length, t = b.length;

        long[][] m = new long[r][c];

        for (int i = 0; i < r; i++) {
            for (int j = 0; j < c; j++) {
                for (int k = 0; k < t; k++) {
                    m[i][j] += a[i][k] * b[k][j];
                }
            }
        }
        return m;
    }

    public static int f1(int n) {
        // base case
        if (n < 3) {
            return n;
        }

        int[] f = new int[n + 1];
        f[0] = 1;
        f[1] = 1;
        for (int i = 2; i <= n; i++) {
            f[i] = f[i - 1] + f[i - 2];
        }
        return f[n];
    }
}
