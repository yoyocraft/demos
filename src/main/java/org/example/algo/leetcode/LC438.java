package org.example.algo.leetcode;

import org.example.algo.ModelParser;
import org.example.algo.OjAssertUtil;
import org.example.algo.constant.SymbolConstant;

import java.util.ArrayList;
import java.util.List;

/**
 * @author yoyocraft
 * @date 2024/09/07
 */
public class LC438 {

    public static void main(String[] args) {
        LC438 lc438 = new LC438();
        OjAssertUtil.judgeResult((tc) -> {
            String[] inOut = tc.split(SymbolConstant.SPACE);
            String s = inOut[0], p = inOut[1];
            String expect = inOut[2];
            List<Integer> actual = lc438.findAnagrams(s, p);
            OjAssertUtil.assertEquals(expect, ModelParser.parseString(actual));
        }, "lc438");
    }

    public List<Integer> findAnagrams(String s, String p) {
        int sn = s.length(), pn = p.length();
        if (sn < pn) {
            return new ArrayList<>();
        }
        char[] ss = s.toCharArray(), ps = p.toCharArray();
        int[] need = new int[26];
        for (char c : ps) {
            need[c - 'a']++;
        }

        int[] win = new int[26];

        List<Integer> ans = new ArrayList<>();
        for (int l = 0, r = 0, cnt = 0; r < sn; ) {
            char c = ss[r++];
            // 字符滑入
            win[c - 'a']++;
            if (win[c - 'a'] <= need[c - 'a']) {
                // 有效字符滑入
                cnt++;
            }

            if (r - l > pn) {
                // 收缩窗口
                c = ss[l++];
                if (win[c - 'a'] <= need[c - 'a']) {
                    // 有效字符滑出
                    cnt--;
                }
                win[c - 'a']--;
            }

            if (cnt == pn) {
                ans.add(l);
            }
        }
        return ans;
    }
}
