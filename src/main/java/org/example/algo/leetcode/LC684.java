package org.example.algo.leetcode;

import org.example.algo.ModelParser;
import org.example.algo.OjAssertUtil;
import org.example.algo.constant.SymbolConstant;

public class LC684 {
    public static void main(String[] args) {
        LC684 lc684 = new LC684();
        OjAssertUtil.judgeResult((tc) -> {
            String[] inOut = tc.split(SymbolConstant.SPACE);
            int[][] edges = ModelParser.parseIntArray2D(inOut[0]);
            String expect = inOut[1];
            int[] actual = lc684.findRedundantConnection(edges);
            OjAssertUtil.assertEquals(expect, ModelParser.parseString(actual));
        }, "lc684");
    }


    int[] fa;

    public int[] findRedundantConnection(int[][] edges) {
        int n = edges.length;
        fa = new int[n + 1];
        for (int i = 1; i <= n; i++) {
            fa[i] = i;
        }

        int[] ret = new int[2];
        for (int[] e : edges) {
            int u = e[0], v = e[1];
            if (!union(u, v)) {
                ret = e;
                break;
            }
        }
        return ret;
    }

    public int find(int x) {
        if (x != fa[x]) {
            fa[x] = find(fa[x]);
        }
        return fa[x];
    }

    public boolean union(int u, int v) {
        int fu = find(u), fv = find(v);
        if (fu == fv) {
            return false;
        }
        fa[fu] = fv;
        return true;
    }

}
