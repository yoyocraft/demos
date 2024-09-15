package org.example.algo.leetcode;

import org.example.algo.ModelParser;
import org.example.algo.OjAssertUtil;
import org.example.algo.constant.SymbolConstant;

/**
 * @author yoyocraft
 * @date 2024/09/15
 */
public class LC64 {
    public static void main(String[] args) {
        LC64 lc64 = new LC64();
        OjAssertUtil.judgeResult((tc) -> {
            String[] inOut = tc.split(SymbolConstant.SPACE);
            int[][] grid = ModelParser.parseIntArray2D(inOut[0]);
            int expect = Integer.parseInt(inOut[1]);
            int actual = lc64.minPathSum(grid);
            OjAssertUtil.assertEquals(expect, actual);
        }, "lc64");
    }

    public int minPathSum(int[][] grid) {
        int m = grid.length;
        int n = grid[0].length;
        for (int i = 1; i < m; i++) {
            grid[i][0] += grid[i - 1][0];
        }
        for (int j = 1; j < n; j++) {
            grid[0][j] += grid[0][j - 1];
        }
        for (int i = 1; i < m; i++) {
            for (int j = 1; j < n; j++) {
                grid[i][j] += Math.min(grid[i - 1][j], grid[i][j - 1]);
            }
        }
        return grid[m - 1][n - 1];
    }
}
