package org.example.algo.leetcode;

import org.example.algo.OjAssertUtil;

/**
 * @author yoyocraft
 * @date 2024/08/27
 */
public class LC567 {
    public static void main(String[] args) {
        LC567 lc = new LC567();
        OjAssertUtil.judgeResult((tc) -> {
            String[] inOut = tc.split(" ");
            String s1 = inOut[0], s2 = inOut[1];
            boolean expect = Boolean.parseBoolean(inOut[2]);
            boolean actual = lc.checkInclusion(s1, s2);
            OjAssertUtil.assertEquals(expect, actual);
        }, "lc567");
    }

    public boolean checkInclusion(String s1, String s2) {
        int n = s1.length(), m = s2.length();
        char[] s1s = s1.toCharArray(), s2s = s2.toCharArray();
        int[] need = new int[26];
        for (char c : s1s) {
            need[c - 'a']++;
        }

        int[] win = new int[26];
        // 统计窗口内的有效字符个数
        int count = 0;
        for (int l = 0, r = 0; r < m; ) {
            char c = s2s[r++];
            win[c - 'a']++;

            // 划入的是有效字符
            if (win[c - 'a'] <= need[c - 'a']) {
                count++;
            }

            if (r - l > n) {
                // 收缩窗口
                c = s2s[l++];
                // 有效字符划出
                if (win[c - 'a'] <= need[c - 'a']) {
                    count--;
                }
                win[c - 'a']--;
            }

            if (count == n) {
                return true;
            }
        }
        return false;
    }
}
