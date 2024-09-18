package org.example.algo.leetcode;

import org.example.algo.ModelParser;
import org.example.algo.OjAssertUtil;
import org.example.algo.constant.SymbolConstant;

import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Deque;
import java.util.List;

/**
 * @author yoyocraft
 * @date 2024/09/18
 */
public class LC210 {
    public static void main(String[] args) {
        LC210 lc210 = new LC210();
        OjAssertUtil.judgeResult((tc) -> {
            String[] inOut = tc.split(SymbolConstant.SPACE);
            int numCourses = Integer.parseInt(inOut[0]);
            int[][] prerequisites = ModelParser.parseIntArray2D(inOut[1]);
            String expect = inOut[2];
            int[] actual = lc210.findOrder(numCourses, prerequisites);
            OjAssertUtil.assertEquals(expect, ModelParser.parseString(actual));
        }, "lc210");
    }

    public int[] findOrder(int numCourses, int[][] prerequisites) {
        int[] inDegree = new int[numCourses];
        List<Integer>[] g = new List[numCourses];
        Arrays.setAll(g, k -> new ArrayList<>());
        for (int[] p : prerequisites) {
            // p[1] -> p[0]
            inDegree[p[0]]++;
            g[p[1]].add(p[0]);
        }

        Deque<Integer> que = new ArrayDeque<>();
        for (int i = 0; i < numCourses; i++) {
            if (inDegree[i] == 0) {
                que.offer(i);
            }
        }

        int[] ans = new int[numCourses];
        int idx = 0;
        while (!que.isEmpty()) {
            int curr = que.poll();
            ans[idx++] = curr;
            numCourses--;
            for (int next : g[curr]) {
                inDegree[next]--;
                if (inDegree[next] == 0) {
                    que.offer(next);
                }
            }
        }
        return numCourses == 0 ? ans : new int[0];
    }
}
