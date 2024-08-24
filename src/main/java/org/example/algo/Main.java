package org.example.algo;

import java.util.Scanner;

/**
 * @author yoyocraft
 * @date 2024/08/24
 */
public class Main {

    static final int MOD = 1000000007;

    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        long a = in.nextLong();
        long b = in.nextLong();
        long c = in.nextLong();
        long k = in.nextLong();

        long s = (a + b + c + k) % MOD;
        long avg = s / 3;

        if (a < avg) {
            long diff = avg - a;
            if (k > diff) {
                a = avg;
                k -= diff;
            } else {
                a += avg - k;
                k = 0;
            }
        }

        if (b < avg) {
            long diff = avg - b;
            if (k > diff) {
                b = avg;
                k -= diff;
            } else {
                b += avg - k;
                k = 0;
            }
        }

        if (c < avg) {
            long diff = avg - c;
            if (k > diff) {
                c = avg;
                k -= diff;
            } else {
                c += avg - k;
                k = 0;
            }
        }

        if (k > 1) {
            b++;
            c++;
        }

        if (k == 1) {
            c++;
        }

        // a * b * c
        long ans = fastMul(a, b, MOD);
        ans = fastMul(ans, c, MOD);
        System.out.println(ans);
    }

    static long fastMul(long a, long b, long mod) {
        long res = 0;
        while (b > 0) {
            if ((b & 1) == 1) {
                res = (res + a) % mod;
            }
            a = (a + a) % mod;
            b >>= 1;
        }
        return res;
    }
}
