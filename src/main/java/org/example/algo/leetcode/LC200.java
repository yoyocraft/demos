package org.example.algo.leetcode;

import org.example.algo.ModelParser;
import org.example.algo.OjAssertUtil;
import org.example.algo.constant.SymbolConstant;

/**
 * @author yoyocraft
 * @date 2024/10/03
 */
public class LC200 {
    public static void main(String[] args) {
        LC200 lc200 = new LC200();
        OjAssertUtil.judgeResult((tc) -> {
            String[] inOut = tc.split(SymbolConstant.SPACE);
            char[][] grid = ModelParser.parseCharacterArray2D(inOut[0]);
            int expect = Integer.parseInt(inOut[1]);
            int actual = lc200.numIslands(grid);
            OjAssertUtil.assertEquals(expect, actual);
        }, "lc200");
    }


    static final int[] dx = new int[]{0, 0, 1, -1};
    static final int[] dy = new int[]{1, -1, 0, 0};
    static final char LAND = '1';
    static final char WATER = '0';

    public int numIslands(char[][] grid) {
        int m = grid.length;
        int n = grid[0].length;
        int landCnt = 0;
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                if (grid[i][j] != LAND) {
                    continue;
                }

                f(grid, i, j);
                landCnt++;
            }
        }
        return landCnt;
    }

    void f(char[][] grid, int x, int y) {
        if (x < 0 || x >= grid.length || y < 0 || y >= grid[0].length || grid[x][y] != LAND) {
            return;
        }

        grid[x][y] = WATER;
        for (int i = 0; i < 4; i++) {
            f(grid, x + dx[i], y + dy[i]);
        }
    }
}
