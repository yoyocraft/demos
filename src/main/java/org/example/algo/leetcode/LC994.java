package org.example.algo.leetcode;

import org.example.algo.ModelParser;
import org.example.algo.OjAssertUtil;
import org.example.algo.constant.SymbolConstant;

import java.util.ArrayDeque;
import java.util.Deque;

/**
 * @author yoyocraft
 * @date 2024/08/31
 */
public class LC994 {

    public static void main(String[] args) {
        LC994 lc994 = new LC994();
        OjAssertUtil.judgeResult((tc) -> {
            String[] inOut = tc.split(SymbolConstant.SPACE);
            int[][] grid = ModelParser.parseIntArray2D(inOut[0]);
            int expect = Integer.parseInt(inOut[1]);
            int actual = lc994.orangesRotting(grid);
            OjAssertUtil.assertEquals(expect, actual);
        }, "lc994");
    }


    private static final int ORANGE = 1;
    private static final int ROTTEN = 2;
    private static final int[] dx = {0, 0, 1, -1};
    private static final int[] dy = {1, -1, 0, 0};

    public int orangesRotting(int[][] grid) {
        int m = grid.length, n = grid[0].length;
        if (n == 0) {
            return 0;
        }
        int freshCnt = 0;
        Deque<int[]> que = new ArrayDeque<>();
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                if (grid[i][j] == ROTTEN) {
                    que.add(new int[]{i, j});
                } else if (grid[i][j] == ORANGE) {
                    freshCnt++;
                }
            }
        }

        int rottenMinutes = 0;
        while (freshCnt > 0 && !que.isEmpty()) {
            rottenMinutes++;
            int size = que.size();
            for (int i = 0; i < size; i++) {
                int[] pos = que.poll();
                for (int k = 0; k < 4; k++) {
                    int nx = pos[0] + dx[k];
                    int ny = pos[1] + dy[k];
                    if (nx < 0 || nx >= m || ny < 0 || ny >= n || grid[nx][ny] != ORANGE) {
                        continue;
                    }

                    grid[nx][ny] = ROTTEN;
                    freshCnt--;
                    que.offer(new int[]{nx, ny});
                }
            }
        }
        return freshCnt > 0 ? -1 : rottenMinutes;
    }
}
