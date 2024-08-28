package org.example.algo.test;

import java.util.Scanner;

/**
 * @author yoyocraft
 * @date 2024/08/25
 */
public class Ant0816_01 {
    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        String s = in.nextLine();
        int n = s.length();
        // 记录最长的重复子串位置
        int pos = -1;
        for (int i = 0; i < n / 2; i++) {
            String sub1 = s.substring(0, i + 1);
            String sub2 = s.substring(i + 1, 2 * (i + 1));
            if (sub1.equals(sub2)) {
                pos = i;
            }
        }

        int ans = 0;
        if (pos == -1) {
            // 没有，全部都是一个一个添加
            ans = n;
        } else {
            ans = n - pos;
        }
        System.out.println(ans);
    }
}
