package org.example.algo.leetcode;

import org.example.algo.ModelParser;
import org.example.algo.OjAssertUtil;
import org.example.algo.constant.SymbolConstant;

/**
 * @author yoyocraft
 * @date 2024/09/16
 */
public class LC73 {
    public static void main(String[] args) {
        LC73 lc73 = new LC73();
        OjAssertUtil.judgeResult((tc) -> {
            String[] inOut = tc.split(SymbolConstant.SPACE);
            int[][] matrix = ModelParser.parseIntArray2D(inOut[0]);
            lc73.setZeroes(matrix);
            OjAssertUtil.assertEquals(inOut[1], ModelParser.parseString(matrix));
        }, "lc73");
    }

    public void setZeroes(int[][] matrix) {
        int m = matrix.length;
        int n = matrix[0].length;

        // 标记原始第一行和第一列是否需要置零
        boolean r0 = false;
        boolean c0 = false;

        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                if (matrix[i][j] != 0) {
                    continue;
                }
                matrix[i][0] = matrix[0][j] = 0;
                if (i == 0) {
                    r0 = true;
                }
                if (j == 0) {
                    c0 = true;
                }
            }
        }

        for (int i = 1; i < m; i++) {
            for (int j = 1; j < n; j++) {
                if (matrix[i][0] == 0 || matrix[0][j] == 0) {
                    matrix[i][j] = 0;
                }
            }
        }

        for (int i = 0; c0 && i < m; i++) {
            matrix[i][0] = 0;
        }
        for (int j = 0; r0 && j < n; j++) {
            matrix[0][j] = 0;
        }
    }
}
