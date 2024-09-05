package org.example.algo.leetcode;

import org.example.algo.ModelParser;
import org.example.algo.OjAssertUtil;
import org.example.algo.constant.SymbolConstant;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * @author yoyocraft
 * @date 2024/09/05
 */
public class LC51 {

    public static void main(String[] args) {
        LC51 lc51 = new LC51();
        OjAssertUtil.judgeResult((tc) -> {
            String[] inOut = tc.split(SymbolConstant.SPACE);
            int n = Integer.parseInt(inOut[0]);
            String expect = inOut[1];
            List<List<String>> actual = lc51.solveNQueens(n);
            OjAssertUtil.assertEquals(expect, ModelParser.parseString(actual));
        }, "lc51");
    }

    List<List<String>> ans;

    /**
     * 记录行、对角线的使用情况
     */
    boolean[] colUse, diagonal45Use, diagonal135Use;

    /**
     * 记录皇后的位置
     */
    int[] board;

    /**
     * 记录行数
     */
    int n;

    public List<List<String>> solveNQueens(int n) {
        this.ans = new ArrayList<>();
        this.colUse = new boolean[n];
        this.diagonal45Use = new boolean[n * 2 - 1];
        this.diagonal135Use = new boolean[n * 2 - 1];
        this.board = new int[n];
        this.n = n;

        f(0);

        return ans;
    }

    void f(int row) {
        if (row == n) {
            // 记录答案
            List<String> path = new ArrayList<>();
            for (int col : board) {
                char[] rowTmp = new char[n];
                Arrays.fill(rowTmp, '.');
                rowTmp[col] = 'Q';
                path.add(new String(rowTmp));
            }
            ans.add(path);
            return;
        }

        for (int col = 0; col < n; col++) {
            // 45度对角线上的元素 col+row 是一个定值
            int diagonal45 = col + row;
            // 135度对角线上的元素 row-col 是一个定值，为了防止后续越界，加上 n-1
            int diagonal135 = row - col + n - 1;

            if (colUse[col] || diagonal45Use[diagonal45] || diagonal135Use[diagonal135]) {
                // 当前位置不合法
                continue;
            }

            // 放置皇后
            board[row] = col;
            colUse[col] = diagonal45Use[diagonal45] = diagonal135Use[diagonal135] = true;
            f(row + 1);
            colUse[col] = diagonal45Use[diagonal45] = diagonal135Use[diagonal135] = false;
        }
    }
}
