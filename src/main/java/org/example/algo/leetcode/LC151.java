package org.example.algo.leetcode;

import org.example.algo.OjAssertUtil;
import org.example.algo.constant.SymbolConstant;

/**
 * @author yoyocraft
 * @date 2024/09/19
 */
public class LC151 {
    public static void main(String[] args) {
        LC151 lc151 = new LC151();
        OjAssertUtil.judgeResult((tc) -> {
            String[] inOut = tc.split(SymbolConstant.HASH);
            String s = inOut[0];
            String expect = inOut[1];
            String actual = lc151.reverseWords(s);
            OjAssertUtil.assertEquals(expect, actual);
        }, "lc151");
    }

    public String reverseWords(String s) {
        if (s == null || s.isEmpty()) {
            return "";
        }
        int n = s.length();
        StringBuilder builder = new StringBuilder();
        int r = n - 1, l = n - 1;
        while (l >= 0) {
            // 倒序寻找第一个单词
            while (l >= 0 && s.charAt(l) != ' ') {
                l--;
            }
            builder.append(s, l + 1, r + 1).append(" ");
            while (l >= 0 && s.charAt(l) == ' ') {
                l--;
            }
            r = l;
        }
        return builder.toString().trim();
    }
}
