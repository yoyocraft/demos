package org.example.algo.leetcode;

import org.example.algo.OjAssertUtil;
import org.example.algo.constant.SymbolConstant;

import java.util.Arrays;

public class LC62 {
    public static void main(String[] args) {
        LC62 lc62 = new LC62();
        OjAssertUtil.judgeResult((tc) -> {
            String[] inOut = tc.split(SymbolConstant.SPACE);
            int m = Integer.parseInt(inOut[0]);
            int n = Integer.parseInt(inOut[1]);
            int expect = Integer.parseInt(inOut[2]);
            int actual = lc62.uniquePaths(m, n);
            OjAssertUtil.assertEquals(expect, actual);
        }, "lc62");
    }

    public int uniquePaths(int m, int n) {
        int[] dp = new int[n];
        Arrays.fill(dp, 1);
        for (int i = 1; i < m; i++) {
            for (int j = 1; j < n; j++) {
                // 等式右边的 dp[j] 是上一次计算后的，加上左边的 dp[j-1] 即为当前结果
                // dp[j] = dp[j] + dp[j - 1]
                // => dp[i][j] = dp[i - 1][j] + dp[i][j - 1]
                dp[j] += dp[j - 1];
            }
        }
        return dp[n - 1];
    }

    private int f1(int m, int n) {
        // 从 m+n-2 中 取 m-1 步即可
        // 只需要往右和下走，因此只需要一个方向走完了，
        // 另外的方向就只有一条路了
        long ans = 1L;
        int loop = Math.min(m - 1, n - 1);
        for (int i = 0; i < loop; i++) {
            ans *= (m + n - 2 - i);
            ans /= (i + 1);
        }
        return (int) ans;
    }
}
