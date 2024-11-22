package org.example.algo.jz;

import org.example.algo.ModelParser;
import org.example.algo.OjAssertUtil;
import org.example.algo.TargetType;
import org.example.algo.constant.SymbolConstant;

public class JZ11 {
    public static void main(String[] args) {
        JZ11 jz11 = new JZ11();
        OjAssertUtil.judgeResult((tc) -> {
            String[] inOut = tc.split(SymbolConstant.SPACE);
            char[][] matrix = ModelParser.parseCharacterArray2D(inOut[0]);
            String word = inOut[1];
            boolean expect = Boolean.parseBoolean(inOut[2]);
            boolean actual = jz11.hasPath(matrix, word);
            OjAssertUtil.assertEquals(expect, actual);
        }, "jz11", TargetType.JZ);
    }

    public boolean hasPath(char[][] matrix, String word) {
        int m = matrix.length, n = matrix[0].length;
        char[] s = word.toCharArray();
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                if (!dfs(matrix, s, i, j, 0)) {
                    continue;
                }
                return true;
            }
        }
        return false;
    }

    boolean dfs(char[][] matrix, char[] s, int x, int y, int i) {
        if (x < 0 || x >= matrix.length || y < 0 || y >= matrix[0].length || matrix[x][y] != s[i]) {
            return false;
        }
        if (i == s.length - 1) {
            return true;
        }

        matrix[x][y] = '\0';
        boolean ans = dfs(matrix, s, x + 1, y, i + 1) ||
                dfs(matrix, s, x, y + 1, i + 1) ||
                dfs(matrix, s, x - 1, y, i + 1) ||
                dfs(matrix, s, x, y - 1, i + 1);
        matrix[x][y] = s[i];
        return ans;
    }
}
