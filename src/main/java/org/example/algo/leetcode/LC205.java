package org.example.algo.leetcode;

import org.example.algo.OjAssertUtil;
import org.example.algo.constant.SymbolConstant;

import java.util.HashMap;
import java.util.Map;

/**
 * @author yoyocraft
 * @date 2024/08/30
 */
public class LC205 {

    public static void main(String[] args) {
        LC205 lc205 = new LC205();
        OjAssertUtil.judgeResult((tc) -> {
            String[] inOut = tc.split(SymbolConstant.SPACE);
            String s = inOut[0], t = inOut[1];
            boolean expect = Boolean.parseBoolean(inOut[2]);
            boolean actual = lc205.isIsomorphic(s, t);
            OjAssertUtil.assertEquals(expect, actual);
        }, "lc205");

    }

    public boolean isIsomorphic(String s, String t) {
        int sn = s.length(), tn = t.length();
        if (sn == 0 || tn == 0) {
            return false;
        }

        char[] ss = s.toCharArray(), ts = t.toCharArray();
        Map<Character, Character> s2t = new HashMap<>();
        Map<Character, Character> t2s = new HashMap<>();

        for (int i = 0; i < sn; i++) {
            char a = ss[i], b = ts[i];
            if ((s2t.containsKey(a) && s2t.get(a) != b)
                    || (t2s.containsKey(b) && t2s.get(b) != a)) {
                return false;
            }
            s2t.put(a, b);
            t2s.put(b, a);
        }

        return true;
    }
}
