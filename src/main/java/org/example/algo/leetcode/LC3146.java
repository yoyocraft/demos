package org.example.algo.leetcode;

import org.example.algo.OjAssertUtil;

/**
 * @author yoyocraft
 * @date 2024/08/24
 */
public class LC3146 {
    public static void main(String[] args) {
        LC3146 lc3146 = new LC3146();
        OjAssertUtil.assertResultWithStream((tcs) -> {
            tcs.forEach(tc -> {
                String[] inOut = tc.split(" ");
                String s = inOut[0], t = inOut[1];
                OjAssertUtil.assertEquals(Integer.parseInt(inOut[2]), lc3146.findPermutationDifference(s, t));
            });
        }, "lc3146");
    }

    public int findPermutationDifference(String s, String t) {
        if (s.equals(t)) {
            return 0;
        }
        int[] sIdx = new int[256];
        char[] ss = s.toCharArray();
        for (int i = 0; i < ss.length; i++) {
            sIdx[ss[i]] = i;
        }
        int ans = 0;
        char[] ts = t.toCharArray();
        for (int i = 0; i < ts.length; i++) {
            char c = ts[i];
            int j = sIdx[c];
            ans += Math.abs(i - j);
        }
        return ans;
    }
}
