package org.example.algo.test;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.io.StreamTokenizer;

public class Main {
    static int n;
    static int k;
    static int[][] g;
    static int maxCnt;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StreamTokenizer in = new StreamTokenizer(br);
        PrintWriter out = new PrintWriter(new OutputStreamWriter(System.out));
        while (in.nextToken() != StreamTokenizer.TT_EOF) {
            n = (int) in.nval;
            in.nextToken();
            int k = (int) in.nval;
            g = new int[k][2];
            for (int i = 0; i < k; i++) {
                in.nextToken();
                g[i][0] = (int) in.nval;
                in.nextToken();
                g[i][1] = (int) in.nval;
            }
            maxCnt = 0;
            f(0, 0);
            out.println(maxCnt);
        }
        out.flush();
        out.close();
        br.close();
    }


    public static void f(int row, int prevMask) {
        if (row >= n) {
            maxCnt = Math.max(maxCnt, Integer.bitCount(prevMask));
            return;
        }

        int currMask = (1 << n) - 1;

        for (int i = 0; i < k; i++) {
            int x = g[i][0];
            int y = g[i][1];
            if (x == row) {
                currMask &= ~(1 << y);
            }
        }

        for (int col = 0; col < n - 1; col++) {
            if ((currMask & (1 << col)) == 0 && (currMask & (1 << (col + 1))) == 0 &&
                    (prevMask & (1 << col)) == 0 && (prevMask & (1 << (col + 1))) == 0) {
                currMask |= (1 << col);
                currMask |= (1 << (col + 1));
                f(row, currMask);
                currMask &= ~(1 << col);
                currMask &= ~(1 << (col + 1));
            }
        }
        f(row + 1, currMask);
    }

}
