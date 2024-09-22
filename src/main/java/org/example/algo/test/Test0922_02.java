package org.example.algo.test;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;

public class Test0922_02 {
    public static void main(String[] args) throws IOException {
        BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
        PrintWriter out = new PrintWriter(new OutputStreamWriter(System.out));
        String word1 = in.readLine();
        String word2 = in.readLine();
        out.println(minDistance(word1, word2));
        out.flush();
        out.close();
        in.close();
    }

    static int minDistance(String word1, String word2) {
        int m = word1.length(), n = word2.length();
        if (m == 0) {
            return n;
        }
        if (n == 0) {
            return m;
        }

        char[] s = word1.toCharArray(), t = word2.toCharArray();
        // f[i+1][j+1]: 表示 s[0...i] 转换成 t[0...j] 的最小次数
        int[][] f = new int[m + 1][n + 1];
        for (int j = 1; j <= n; j++) {
            f[0][j] = j;
        }
        for (int i = 1; i <= m; i++) {
            f[i][0] = i;
        }

        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                if (s[i] == t[j]) {
                    f[i + 1][j + 1] = f[i][j];
                } else {
                    f[i + 1][j + 1] = Math.min(
                            f[i + 1][j],
                            Math.min(f[i][j + 1], f[i][j])
                    ) + 1;
                }
            }
        }
        return f[m][n];
    }
}