package org.example.algo.test;

import java.util.Arrays;
import java.util.Scanner;

public class Test0922_01 {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        int n = scanner.nextInt();
        int T = scanner.nextInt();
        int[] times = new int[n];
        int[] scores = new int[n];

        for (int i = 0; i < n; i++) {
            times[i] = scanner.nextInt();
        }
        for (int i = 0; i < n; i++) {
            scores[i] = scanner.nextInt();
        }

        int[][] f = new int[n + 1][T + 1];

        for (int i = 0; i <= n; i++) {
            Arrays.fill(f[i], 0);
        }

        for (int i = 1; i <= n; i++) {
            for (int j = 0; j <= T; j++) {
                if (times[i - 1] > j) {
                    f[i][j] = f[i - 1][j];
                } else {
                    // 否则可以选择或者不选择这个任务
                    f[i][j] = Math.max(f[i - 1][j], f[i - 1][j - times[i - 1]] + scores[i - 1]);
                }
            }
        }

        System.out.println(f[n][T]);

        scanner.close();
    }
}