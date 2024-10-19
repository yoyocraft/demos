package org.example.algo.leetcode;

import org.example.algo.OjAssertUtil;
import org.example.algo.constant.SymbolConstant;

import java.util.ArrayDeque;
import java.util.Deque;

/**
 * @author yoyocraft
 * @date 2024/10/19
 */
public class LC394 {
    public static void main(String[] args) {
        LC394 lc394 = new LC394();
        OjAssertUtil.judgeResult((tc) -> {
            String[] inOut = tc.split(SymbolConstant.SPACE);
            String s = inOut[0];
            String expect = inOut[1];
            String actual = lc394.decodeString(s);
            OjAssertUtil.assertEquals(expect, actual);
        }, "lc394");
    }

    public String decodeString(String s) {
        StringBuilder ret = new StringBuilder();
        int multi = 0;
        Deque<Integer> multiStk = new ArrayDeque<>();
        Deque<String> strStk = new ArrayDeque<>();
        for (char c : s.toCharArray()) {
            if (c == '[') {
                multiStk.push(multi);
                strStk.push(ret.toString());
                multi = 0;
                ret = new StringBuilder();
            } else if (c == ']') {
                StringBuilder tmp = new StringBuilder();
                int curMulti = multiStk.pop();
                for (int i = 0; i < curMulti; i++) {
                    tmp.append(ret);
                }
                ret = new StringBuilder().append(strStk.pop()).append(tmp);
            } else if (c >= '0' && c <= '9') {
                multi = multi * 10 + (c - '0');
            } else {
                // c is a letter
                ret.append(c);
            }
        }
        return ret.toString();
    }
}
