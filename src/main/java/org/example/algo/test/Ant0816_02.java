package org.example.algo.test;

import java.util.Scanner;

/**
 * @author yoyocraft
 * @date 2024/08/25
 */
public class Ant0816_02 {
    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        int n = in.nextInt();
        int q = in.nextInt();
        long s = 0;
        int even = 0, odd = 0;
        for (int i = 0; i < n; i++) {
            int x = in.nextInt();
            if (x % 2 == 0) {
                even++;
            } else {
                odd++;
            }
            s += x;
        }

        for (int i = 0; i < q; i++) {
            int op = in.nextInt();
            if (op == 1) {
                int x = in.nextInt();
                int y = in.nextInt();
                if (x == 1) {
                    // 奇数加上 y
                    s += (long) y * odd;
                } else {
                    // 偶数加上 y
                    s += (long) y * even;
                }

                if ((y & 1) == 1) {
                    if (x == 1) {
                        // 给所有奇数加上一个奇数
                        even += odd;
                        odd = 0;
                    } else {
                        // 给所有的偶数加上一个奇数
                        odd += even;
                        even = 0;
                    }
                }
            } else if (op == 2) {
                System.out.println(s);
            }
        }
    }
}
