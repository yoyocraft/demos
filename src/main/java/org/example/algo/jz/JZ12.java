package org.example.algo.jz;

import org.example.algo.OjAssertUtil;
import org.example.algo.TargetType;
import org.example.algo.constant.SymbolConstant;

/**
 * <a href="https://www.nowcoder.com/practice/6e5207314b5241fb83f2329e89fdecc8">JZ12</a>
 * <p>
 * 地上有一个 rows 行和 cols 列的方格。坐标从 [0,0] 到 [rows-1,cols-1] 。
 * 一个机器人从坐标 [0,0] 的格子开始移动，每一次只能向左，右，上，下四个方向移动一格，
 * 但是不能进入行坐标和列坐标的数位之和大于 threshold 的格子。
 * 例如，当 threshold 为 18 时，机器人能够进入方格   [35,37] ，因为 3+5+3+7 = 18。
 * 但是，它不能进入方格 [35,38] ，因为 3+5+3+8 = 19 。请问该机器人能够达到多少个格子？
 * 数据范围：0 <= threshold <= 15, 1 <= rows, cols <= 100
 * 进阶：空间复杂度 O(nm), 时间复杂度 O(nm)
 */
public class JZ12 {

    public static void main(String[] args) {
        JZ12 jz12 = new JZ12();
        OjAssertUtil.judgeResult((tc) -> {
            String[] inOut = tc.split(SymbolConstant.SPACE);
            int threshold = Integer.parseInt(inOut[0]);
            int rows = Integer.parseInt(inOut[1]);
            int cols = Integer.parseInt(inOut[2]);
            int expect = Integer.parseInt(inOut[3]);
            int actual = jz12.movingCount(threshold, rows, cols);
            OjAssertUtil.assertEquals(expect, actual);
        }, "jz12", TargetType.JZ);
    }

    int ans = 0;

    public int movingCount(int threshold, int rows, int cols) {
        ans = 0;
        int[][] g = compute(Math.max(rows, cols));
        boolean[][] vis = new boolean[rows][cols];
        f(0, 0, rows, cols, threshold, vis, g);
        return ans;
    }

    void f(int x, int y, int rows, int cols, int threshold, boolean[][] vis, int[][] g) {
        if (x < 0 || x >= rows || y < 0 || y >= cols || vis[x][y] || g[x][y] > threshold) {
            return;
        }

        ans++;
        vis[x][y] = true;
        f(x + 1, y, rows, cols, threshold, vis, g);
        f(x, y + 1, rows, cols, threshold, vis, g);
        f(x - 1, y, rows, cols, threshold, vis, g);
        f(x, y - 1, rows, cols, threshold, vis, g);
    }

    int[][] compute(int n) {
        int[][] g = new int[n][n];
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                if (g[i][j] != 0) {
                    g[j][i] = g[i][j];
                } else {
                    g[i][j] = cal(i) + cal(j);
                }
            }
        }
        return g;
    }

    int cal(int x) {
        int s = 0;
        while (x != 0) {
            s += x % 10;
            x /= 10;
        }
        return s;
    }
}
