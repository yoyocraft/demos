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
public class LC207 {
    public static void main(String[] args) {
        LC207 lc207 = new LC207();
        OjAssertUtil.judgeResult((tc) -> {
            String[] inOut = tc.split(SymbolConstant.SPACE);
            int numCourses = Integer.parseInt(inOut[0]);
            int[][] prerequisites = ModelParser.parseIntArray2D(inOut[1]);
            boolean expect = Boolean.parseBoolean(inOut[2]);
            boolean actual = lc207.canFinish(numCourses, prerequisites);
            OjAssertUtil.assertEquals(expect, actual);
        }, "lc207");
    }

    public boolean canFinish(int numCourses, int[][] prerequisites) {
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

        while (!que.isEmpty()) {
            int curr = que.poll();
            numCourses--;
            for (int next : g[curr]) {
                inDegree[next]--;
                if (inDegree[next] == 0) {
                    que.offer(next);
                }
            }
        }
        return numCourses == 0;
    }
}
