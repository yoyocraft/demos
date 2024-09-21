package org.example.algo.test;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.util.ArrayDeque;
import java.util.Arrays;
import java.util.Deque;

/**
 * @author yoyocraft
 * @date 2024/09/21
 */
public class Wangyi0921_02 {
    public static void main(String[] args) throws IOException {
        BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
        PrintWriter out = new PrintWriter(new OutputStreamWriter(System.out));
        String line = in.readLine();
        out.println(solve(line) ? "true" : "false");
        // write code here
        out.flush();
        out.close();
        in.close();
    }

    static boolean solve(String line) {
        int[] nums = Arrays.stream(line.split(",")).mapToInt(Integer::valueOf).toArray();
        int maxCover = 0;
        int n = nums.length;
        for (int i = 0; i <= maxCover; i++) {
            maxCover = Math.max(maxCover, i + nums[i]);
            if (maxCover >= n) {
                return true;
            }
        }
        return false;
    }

}
