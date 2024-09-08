package org.example.algo;

import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        int n = in.nextInt();
        int k = in.nextInt();


        int[][] cost = new int[k][k];
        for (int i = 0; i < k; i++) {
            for (int j = 0; j < k; j++) {
                cost[i][j] = in.nextInt();
            }
        }

        in.nextLine();
        String s = in.nextLine();
        char[] ss = s.toCharArray();

        int[][] dp = new int[n][n];
        for (int len = 2; len <= n; len += 2) {
            for (int i = 0; i <= n - len; i++) {
                int j = i + len - 1;
                for (int m = i; m < j; m += 2) {
                    dp[i][j] = Math.max(dp[i][j], dp[i][m] + dp[m + 1][j] + cost[ss[i] - 'a'][ss[m + 1] - 'a']);
                }
            }
        }

        System.out.println(dp[0][n - 1]);
    }
}