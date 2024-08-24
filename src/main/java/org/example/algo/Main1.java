package org.example.algo;


import java.util.Scanner;

public class Main1 {
    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        long a = in.nextLong();
        long b = in.nextLong();
        long c = in.nextLong();
        long d = in.nextLong();

        int n = in.nextInt();
        long tmpDisE = 0, ans = 0;
        long minDisO = Integer.MAX_VALUE;
        for (int i = 0; i < n; i++) {
            long x = in.nextLong();
            long y = in.nextLong();

            long disO = getDis(a, b, x, y);
            long disE = getDis(c, d, x, y);
            if (disO < minDisO) {
                minDisO = disO;
                tmpDisE = disE;
            }
            ans += disE;
        }

        ans = fastMul(ans, 2);
        ans -= tmpDisE;
        ans += minDisO;
        System.out.println(ans);
    }

    static long fastMul(long a, long b) {
        long res = 0;
        while (b > 0) {
            if ((b & 1) == 1) {
                res += a;
            }
            a <<= 1;
            b >>= 1;
        }
        return res;
    }

    static long getDis(long x1, long y1, long x2, long y2) {
        return Math.abs(x1 - x2) + Math.abs(y1 - y2);
    }
}