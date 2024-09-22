package org.example.algo;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * @author yoyocraft
 * @date 2024/09/22
 */
public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
        PrintWriter out = new PrintWriter(new OutputStreamWriter(System.out));

        int T = Integer.parseInt(in.readLine());
        for (int k = 0; k < T; k++) {
            List<Set<String>> prefixSet = new ArrayList<>();
            String s = in.readLine();
            String[] inOut = s.split(" ");
            int n = Integer.parseInt(inOut[0]);
            int x = Integer.parseInt(inOut[1]);
            List<List<String>> g = new ArrayList<>();
            Set<String> currentSet = new HashSet<>();
            for (int i = 0; i < n; i++) {
                s = in.readLine();
                inOut = s.split(" ");
                int m = Integer.parseInt(inOut[0]);
                List<String> gi = new ArrayList<>(m);
                gi.addAll(Arrays.asList(inOut).subList(1, m + 1));
                g.add(gi);
                currentSet = new HashSet<>(currentSet);
                currentSet.addAll(gi);
                prefixSet.add(currentSet);
            }

            solve(x, g, prefixSet, out);
        }
        out.flush();
        out.close();
        in.close();
    }

    private static void solve(int x, List<List<String>> g, List<Set<String>> prefixSet, PrintWriter out) {
        int n = g.size();
        for (int i = 0; i < n; i++) {
            for (int j = i; j < n; j++) {
                Set<String> combinedSet = new HashSet<>(prefixSet.get(j));
                if (i > 0) {
                    combinedSet.removeAll(prefixSet.get(i - 1));
                }
                if (combinedSet.size() == x) {
                    out.println("YES");
                    out.println((i + 1) + " " + (j + 1));
                    return;
                }
            }
        }
        out.println("NO");

    }
}
