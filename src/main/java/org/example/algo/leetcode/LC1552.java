package org.example.algo.leetcode;

import org.example.algo.ModelParser;
import org.example.algo.OjAssertUtil;
import org.example.algo.constant.SymbolConstant;

import java.util.Arrays;

/**
 * @author yoyocraft
 * @date 2024/09/06
 */
public class LC1552 {
    public static void main(String[] args) {
        LC1552 lc1552 = new LC1552();
        OjAssertUtil.judgeResult((tc) -> {
            String[] inOut = tc.split(SymbolConstant.SPACE);
            int[] position = ModelParser.parseIntArray(inOut[0]);
            int m = Integer.parseInt(inOut[1]);
            int expect = Integer.parseInt(inOut[2]);
            int actual = lc1552.maxDistance(position, m);
            OjAssertUtil.assertEquals(expect, actual);
        }, "lc1552");
    }

    public int maxDistance(int[] position, int m) {
        Arrays.sort(position);
        int l = 0, r = position[position.length - 1], ans = -1;
        while (l <= r) {
            int mid = l + (r - l) / 2;
            if (check(position, m, mid)) {
                ans = mid;
                l = mid + 1;
            } else {
                r = mid - 1;
            }
        }
        return ans;
    }

    boolean check(int[] position, int m, int dis) {
        int cnt = 1;
        int lastDis = position[0];
        for (int i = 1; i < position.length && cnt < m; i++) {
            if (position[i] >= lastDis + dis) {
                cnt++;
                lastDis = position[i];
            }
        }
        return cnt >= m;
    }
}
