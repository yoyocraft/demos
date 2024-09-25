package org.example.algo.test;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.io.StreamTokenizer;

public class Main_03 {
    static int n;
    static int maxCnt;
    static boolean[][] g;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StreamTokenizer in = new StreamTokenizer(br);
        PrintWriter out = new PrintWriter(new OutputStreamWriter(System.out));
        while (in.nextToken() != StreamTokenizer.TT_EOF) {
            n = (int) in.nval;
            in.nextToken();
            int k = (int) in.nval;
            g = new boolean[n][n];
            for (int i = 0; i < k; i++) {
                in.nextToken();
                int x = (int) in.nval;
                in.nextToken();
                int y = (int) in.nval;
                g[x][y] = true;
            }
            maxCnt = 0;
            f(0);
            out.println(maxCnt);
        }
        out.flush();
        out.close();
        br.close();
    }

    static boolean check(int r, int c) {
        return r + 1 < n && c + 1 < n &&
                !g[r][c] && !g[r + 1][c] &&
                !g[r][c + 1] && !g[r + 1][c + 1];
    }

    static void doPlace(int r, int c, boolean p) {
        g[r][c] = p;
        g[r + 1][c] = p;
        g[r][c + 1] = p;
        g[r + 1][c + 1] = p;
    }

    static void f(int cnt) {
        maxCnt = Math.max(maxCnt, cnt);

        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                if (check(i, j)) {
                    doPlace(i, j, true);
                    f(cnt + 1);
                    doPlace(i, j, false);
                }
            }
        }
    }

}
