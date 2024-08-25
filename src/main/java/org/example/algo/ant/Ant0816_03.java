package org.example.algo.ant;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Scanner;

/**
 * @author yoyocraft
 * @date 2024/08/25
 */
public class Ant0816_03 {
    static final int N = 510;
    static int[][] g = new int[N][N];
    static int n, m;
    static long ans = 0;

    static long get(List<Long> t, int k) {
        t.sort(Collections.reverseOrder());
        long s = 0;
        for (int i = 0; i < k; i++) {
            s += t.get(i);
        }
        return s;
    }

    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        n = in.nextInt();
        m = in.nextInt();

        for (int i = 0; i < n; i++) {
            for (int j = 0; j < m; j++) {
                g[i][j] = in.nextInt();
            }
        }

        List<Long> rows = new ArrayList<>();
        for (int i = 0; i < Math.max(n, 3); i++) {
            long s = 0;
            for (int j = 0; j < m; j++) {
                s += g[i][j];
            }
            rows.add(s);
        }

        List<Long> cols = new ArrayList<>();
        for (int j = 0; j < Math.max(m, 3); j++) {
            long s = 0;
            for (int i = 0; i < n; i++) {
                s += g[i][j];
            }
            cols.add(s);
        }

        // 取行和列的最大值
        ans = Math.max(get(new ArrayList<>(rows), 3), get(new ArrayList<>(cols), 3));

        // 固定一行，取最大的其他两列
        for (int i = 0; i < Math.max(n, 3); i++) {
            long row = rows.get(i);
            List<Long> t = new ArrayList<>();
            for (int j = 0; j < cols.size(); j++) {
                long s = cols.get(j) - g[i][j];
                t.add(s);
            }
            ans = Math.max(ans, row + get(t, 2));
        }
        // 固定一列，取最大的其他两行
        for (int j = 0; j < Math.max(m, 3); j++) {
            long col = cols.get(j);
            List<Long> t = new ArrayList<>();
            for (int i = 0; i < rows.size(); i++) {
                long s = rows.get(i) - g[i][j];
                t.add(s);
            }
            ans = Math.max(ans, col + get(t, 2));
        }

        System.out.println(ans);
    }
}
