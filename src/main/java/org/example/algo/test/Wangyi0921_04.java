package org.example.algo.test;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.util.ArrayDeque;
import java.util.Deque;

/**
 * @author yoyocraft
 * @date 2024/09/21
 */
public class Wangyi0921_04 {
    public static void main(String[] args) throws IOException {
        BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
        PrintWriter out = new PrintWriter(new OutputStreamWriter(System.out));
        String line = in.readLine();
        out.println(solve(line));
        // write code here
        out.flush();
        out.close();
        in.close();
    }

    static int solve(String s) {
        int n = s.length();
        if (n < 2) {
            return 0;
        }

        int[] f = new int[n];
        int ans = 0;
        for (int i = 1; i < n; i++) {
            if (s.charAt(i) == '(') {
                continue;
            }

            // s[i] == ')'
            if (s.charAt(i - 1) == '(') {
                // '()'
                f[i] = 2;
                if (i - 2 >= 0) {
                    f[i] += f[i - 2];
                }
            } else if (f[i - 1] > 0) {
                // '((..))'
                int lastIdx = i - f[i - 1] - 1;
                if (lastIdx >= 0 && s.charAt(lastIdx) == '(') {
                    f[i] = f[i - 1] + 2;
                    if (lastIdx - 1 >= 0) {
                        // '(..)((..))'
                        f[i] += f[lastIdx - 1];
                    }
                }
            }
            ans = Math.max(ans, f[i]);
        }
        return ans;
    }

}
