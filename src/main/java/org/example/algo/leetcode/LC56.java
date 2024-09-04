package org.example.algo.leetcode;

import org.example.algo.ModelParser;
import org.example.algo.OjAssertUtil;
import org.example.algo.constant.SymbolConstant;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * @author yoyocraft
 * @date 2024/09/04
 */
public class LC56 {
    public static void main(String[] args) {
        LC56 lc56 = new LC56();
        OjAssertUtil.judgeResult((tc) -> {
            String[] inOut = tc.split(SymbolConstant.SPACE);
            int[][] intervals = ModelParser.parseIntArray2D(inOut[0]);
            String expect = inOut[1];
            int[][] actual = lc56.merge(intervals);
            OjAssertUtil.assertEquals(expect, ModelParser.parseString(actual));
        }, "lc56");
    }

    public int[][] merge(int[][] intervals) {
        if (intervals == null || intervals.length <= 1) {
            return intervals;
        }
        int n = intervals.length;
        Arrays.sort(intervals, (a, b) -> a[0] - b[0]);
        List<int[]> ans = new ArrayList<>(n);
        for (int[] p : intervals) {
            int i = ans.size();
            if (i > 0 && ans.get(i - 1)[1] >= p[0]) {
                ans.get(i - 1)[1] = Math.max(ans.get(i - 1)[1], p[1]);
            } else {
                ans.add(p);
            }
        }
        return ans.toArray(new int[ans.size()][]);
    }
}
