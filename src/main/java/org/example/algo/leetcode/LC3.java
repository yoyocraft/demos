package org.example.algo.leetcode;

import org.example.algo.OjAssertUtil;
import org.example.algo.constant.SymbolConstant;

/**
 * @author yoyocraft
 * @date 2024/09/02
 */
public class LC3 {

    public static void main(String[] args) {
        LC3 lc3 = new LC3();
        OjAssertUtil.judgeResult((tc) -> {
            String[] inOut = tc.split(SymbolConstant.SPACE);
            String str = inOut[0];
            int expect = Integer.parseInt(inOut[1]);
            int actual = lc3.lengthOfLongestSubstring(str);
            OjAssertUtil.assertEquals(expect, actual);
        }, "lc3");
    }

    public int lengthOfLongestSubstring(String str) {
        if (str == null || str.isEmpty()) {
            return 0;
        }

        int ans = 0;
        char[] s = str.toCharArray();
        boolean[] win = new boolean[128];
        for (int l = 0, r = 0; r < s.length; ) {
            while (win[s[r]]) {
                win[s[l++]] = false;
            }
            win[s[r++]] = true;
            ans = Math.max(ans, r - l);
        }
        return ans;
    }
}
