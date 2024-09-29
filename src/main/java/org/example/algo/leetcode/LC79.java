package org.example.algo.leetcode;

import org.example.algo.ModelParser;
import org.example.algo.OjAssertUtil;
import org.example.algo.constant.SymbolConstant;

/**
 * @author yoyocraft
 * @date 2024/09/29
 */
public class LC79 {

    public static void main(String[] args) {
        LC79 lc79 = new LC79();
        OjAssertUtil.judgeResult((tc) -> {
            String[] inOut = tc.split(SymbolConstant.SPACE);
            char[][] board = ModelParser.parseCharacterArray2D(inOut[0]);
            String word = inOut[1];
            boolean expect = Boolean.parseBoolean(inOut[2]);
            boolean actual = lc79.exist(board, word);
            OjAssertUtil.assertEquals(expect, actual);
        }, "lc79");
    }

    public boolean exist(char[][] board, String word) {
        int m = board.length;
        int n = board[0].length;
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                if (f(board, word, i, j, 0)) {
                    return true;
                }
            }
        }
        return false;
    }

    boolean f(char[][] board, String word, int i, int j, int idx) {
        if (i < 0 || i >= board.length || j < 0 || j >= board[0].length || board[i][j] != word.charAt(idx)) {
            return false;
        }

        if (idx == word.length() - 1) {
            return true;
        }

        board[i][j] = '\0';

        if (f(board, word, i + 1, j, idx + 1) ||
                f(board, word, i - 1, j, idx + 1) ||
                f(board, word, i, j + 1, idx + 1) ||
                f(board, word, i, j - 1, idx + 1)) {
            return true;
        }

        board[i][j] = word.charAt(idx);
        return false;
    }
}
