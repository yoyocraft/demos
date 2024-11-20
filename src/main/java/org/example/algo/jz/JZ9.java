package org.example.algo.jz;

import org.example.algo.OjAssertUtil;
import org.example.algo.TargetType;
import org.example.algo.constant.SymbolConstant;

/**
 * <a href="https://www.nowcoder.com/practice/c6c7742f5ba7442aada113136ddea0c3">JZ9</a>
 * <p>
 * 输入一个正整数 n ，请你输出斐波那契数列的第 n 项。
 * 数据范围: 1 <= n <= 40
 * 要求：空间复杂度 O(1), 时间复杂度 O(n)
 * 进阶要求：时间复杂度 O(logN)
 */
public class JZ9 {

    public static void main(String[] args) {
        JZ9 jz9 = new JZ9();
        OjAssertUtil.judgeResult((tc) -> {
            String[] inOut = tc.split(SymbolConstant.SPACE);
            int n = Integer.parseInt(inOut[0]);
            int expect = Integer.parseInt(inOut[1]);
            int actual = jz9.Fibonacci(n);
            OjAssertUtil.assertEquals(expect, actual);
        }, "jz9", TargetType.JZ);
    }

    public int Fibonacci(int n) {
        return f2(n);
    }

    public int f2(int n) {
        if (n == 1 || n == 2) {
            return 1;
        }

        long[][] mat = {
                {1, 1},
                {1, 0}
        };
        long[][] ans = {
                {1},
                {0}
        };

        int x = n - 1;
        while (x != 0) {
            if ((x & 1) != 0) {
                ans = mul(mat, ans);
            }
            x >>>= 1;
            mat = mul(mat, mat);
        }
        return (int) ans[0][0];
    }

    private long[][] mul(long[][] a, long[][] b) {
        int r = a.length, c = b[0].length, z = b.length;
        long[][] ans = new long[r][c];
        for (int i = 0; i < r; i++) {
            for (int j = 0; j < c; j++) {
                for (int k = 0; k < z; k++) {
                    ans[i][j] += a[i][k] * b[k][j];
                }
            }
        }
        return ans;
    }

    public int f1(int n) {
        if (n == 1 || n == 2) {
            return 1;
        }
        int f0 = 1, f1 = 1, f2 = 0;
        for (int i = 3; i <= n; i++) {
            f2 = f1 + f0;
            f0 = f1;
            f1 = f2;
        }
        return f2;
    }
}
