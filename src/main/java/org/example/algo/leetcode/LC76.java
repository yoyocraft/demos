package org.example.algo.leetcode;

import org.example.algo.OjAssertUtil;


/**
 * @author yoyocraft
 * @date 2024/08/22
 */
public class LC76 {

    public static void main(String[] args) {
        LC76 lc76 = new LC76();
        OjAssertUtil.judgeResult((tc) -> {
            String[] singleTc = tc.split("#");
            String ss = singleTc[0], ts = singleTc[1], expect = singleTc[2];
            String actual = lc76.minWindow(ss, ts);
            OjAssertUtil.assertEquals(expect, actual);
        }, "lc76");
    }

    public String minWindow(String ss, String ts) {
        int sl = ss.length(), tl = ts.length();
        if (sl == 0 || tl == 0 || sl < tl) {
            return "";
        }

        char[] s = ss.toCharArray(), t = ts.toCharArray();
        // 总共需要的字符数，便于判断是否收集完全
        int debt = tl;
        int[] need = new int[256];
        for (char c : t) {
            need[c]--;
        }

        // 划窗
        int l = 0, r = 0, start = 0, len = Integer.MAX_VALUE;
        while (r < sl) {
            // 字符划入窗口
            char c = s[r];
            r++;
            if (need[c]++ < 0) {
                debt--;
            }

            if (debt == 0) {
                // 多余字符划出
                while (need[s[l]] > 0) {
                    need[s[l]]--;
                    l++;
                }

                if (r - l < len) {
                    len = r - l;
                    start = l;
                }
            }
        }

        return len == Integer.MAX_VALUE ? "" : ss.substring(start, start + len);
    }
}
