package org.example.algo.test;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.List;
import java.util.PriorityQueue;

/**
 * @author yoyocraft
 * @date 2024/09/05
 */
public class Didi230915_02 {

    static final int[] dx = {0, 1, 0, -1};
    static final int[] dy = {1, 0, -1, 0};

    static final int INF = 0x3f3f3f3f;


    public static void main(String[] args) throws IOException {
        BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
        PrintWriter out = new PrintWriter(new OutputStreamWriter(System.out));
        String[] s = in.readLine().split(" ");
        int n = Integer.parseInt(s[0]);
        int m = Integer.parseInt(s[1]);
        int[][] grid = new int[n][m];
        for (int i = 0; i < n; i++) {
            s = in.readLine().split(" ");
            for (int j = 0; j < m; j++) {
                grid[i][j] = Integer.parseInt(s[j]);
            }
        }

        // 邻接表建图
        List<int[]>[] g = new ArrayList[m * n];
        Arrays.setAll(g, k -> new ArrayList<>());
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < m; j++) {
                int idx = i * m + j;
                for (int k = 0; k < 4; k++) {
                    int nx = i + dx[k];
                    int ny = j + dy[k];
                    if (nx < 0 || nx >= n || ny < 0 || ny >= m) {
                        continue;
                    }
                    g[idx].add(new int[]{nx * m + ny, grid[nx][ny]});
                }
            }
        }

        int[] dist = new int[m * n];
        Arrays.fill(dist, INF);
        dist[0] = grid[0][0];
        boolean[] vis = new boolean[m * n];

        PriorityQueue<int[]> q = new PriorityQueue<>(Comparator.comparingInt(a -> a[1]));
        q.offer(new int[]{0, dist[0]});
        while (!q.isEmpty()) {
            int[] pos = q.poll();
            int idx = pos[0];
            if (vis[idx]) {
                continue;
            }
            vis[idx] = true;

            for (int[] p : g[idx]) {
                int end = p[0];
                if (dist[end] > dist[idx] + p[1]) {
                    dist[end] = dist[idx] + p[1];
                    q.offer(new int[]{end, dist[end]});
                }
            }
        }

        out.println(dist[m * n - 1]);

        out.flush();
        in.close();
        out.close();
    }
}

