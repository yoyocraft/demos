package org.example.algo.test;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.io.StreamTokenizer;


/**
 * 在俄罗听方块游戏中，只有下面1种大方块，由四个正方形小方块组成。现在，请计算在给定网格大小的情况下，最多可以放置多少个大方块。具体规则如下：
 * 1. 网格为正方形网格
 * 2. 方块不能重叠
 * 3. 方块不能超出网格的边界。
 * 4. 网格中部分位置不能放置方块
 * <p>
 * 输入：
 * n k
 * x1 y1
 * x2 y2
 * 表示边长为n的正方形网格，有k个位置不能放置方块，接下来k行坐标
 * 对（坐标从0开始编号，左上角为0 0）
 * n 的范围: [1,8]
 * k 的范围: [0,64]
 * x,y 的范围: [0, n)
 * <p>
 * 输出：最多能放下多少大方块
 *
 * 示例输入1
 * 输入: 2 0
 * 输出: 1
 *
 * 示例输入2
 * 输入:
 * 	4 3
 * 	1 0
 * 	1 3
 * 	2 1
 * 输出: 2
 *
 * 示例输入3
 * 输入:3 3
 *     0 1
 *     1 2
 *     2 0
 * 输出: 0
 */
public class Main_02 {

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StreamTokenizer in = new StreamTokenizer(br);
        PrintWriter out = new PrintWriter(new OutputStreamWriter(System.out));
        while (in.nextToken() != StreamTokenizer.TT_EOF) {
            int n = (int) in.nval;
            in.nextToken();
            int k = (int) in.nval;
            int[][] blockedPos = new int[k][2];
            for (int i = 0; i < k; i++) {
                in.nextToken();
                blockedPos[i][0] = (int) in.nval;
                in.nextToken();
                blockedPos[i][1] = (int) in.nval;
            }
            out.println(maxBlocks(n, blockedPos));
        }
        out.flush();
        out.close();
        br.close();
    }

    static boolean check(int[][] grid, int i, int j) {
        return i + 1 < grid.length && j + 1 < grid[0].length &&
                grid[i][j] == 0 && grid[i + 1][j] == 0 &&
                grid[i][j + 1] == 0 && grid[i + 1][j + 1] == 0;
    }

    static void doPlace(int[][] grid, int i, int j) {
        grid[i][j] = 1;
        grid[i + 1][j] = 1;
        grid[i][j + 1] = 1;
        grid[i + 1][j + 1] = 1;
    }

    public static int maxBlocks(int n, int[][] blockedPos) {
        int[][] g = new int[n][n];
        for (int[] p : blockedPos) {
            g[p[0]][p[1]] = 1;
        }

        int count = 0;

        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                if (check(g, i, j)) {
                    doPlace(g, i, j);
                    count++;
                }
            }
        }

        return count;
    }


}


