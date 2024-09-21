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
public class Wangyi0921_01 {
    public static void main(String[] args) throws IOException {
        BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
        PrintWriter out = new PrintWriter(new OutputStreamWriter(System.out));
        String line = in.readLine();
        int ans = solve(line) ? 1 : 0;
        out.println(ans);
        out.flush();
        out.close();
        in.close();
    }

    static boolean solve(String s) {
        int n = s.length();
        if (n == 1) {
            return false;
        }
        Deque<Character> st = new ArrayDeque<>(n);
        for (int i = 0; i < n; i++) {
            char c = s.charAt(i);
            if (c == '(') {
                st.push(')');
            } else if (c == '[') {
                st.push(']');
            } else if (c == '{') {
                st.push('}');
            } else {
                if (st.isEmpty() || st.pop() != c) {
                    return false;
                }
            }
        }
        return st.isEmpty();
    }

}
