package org.example.algo.leetcode;

import org.example.algo.ModelParser;
import org.example.algo.OjAssertUtil;
import org.example.algo.constant.SymbolConstant;

import java.util.ArrayList;
import java.util.List;

public class LC131 {

    public static void main(String[] args) {
        LC131 lc131 = new LC131();
        OjAssertUtil.judgeResult((tc) -> {
            String[] inOut = tc.split(SymbolConstant.SPACE);
            String s = inOut[0];
            String expect = inOut[1];
            List<List<String>> actual = lc131.partition(s);
            OjAssertUtil.assertEquals(expect, ModelParser.parseString(actual));
        }, "lc131");
    }

    public List<List<String>> partition(String s) {
        List<List<String>> ans = new ArrayList<>();
        int n = s.length();
        if (n == 0) {
            return ans;
        }

        List<String> path = new ArrayList<>();
        // palindrome[i][j]: s[i...j] 是回文串
        boolean[][] palindrome = new boolean[n][n];
        // 提前计算
        permute(s.toCharArray(), palindrome);

        f(s, 0, palindrome, path, ans);

        return ans;
    }

    void f(String s, int i, boolean[][] palindrome,
           List<String> path, List<List<String>> ans) {
        if (i >= s.length()) {
            ans.add(new ArrayList<>(path));
            return;
        }

        for (int j = i; j < s.length(); j++) {
            if (!palindrome[i][j]) {
                continue;
            }

            // s[i...j] 是回文串
            path.add(s.substring(i, j + 1));
            f(s, j + 1, palindrome, path, ans);
            path.remove(path.size() - 1);
        }
    }

    void permute(char[] s, boolean[][] palindrome) {
        // 计算 palindrome[i][j]
        // i <= j
        for (int i = s.length - 1; i >= 0; i--) {
            for (int j = i; j < s.length; j++) {
                if (i == j) {
                    // 一个字母
                    palindrome[i][j] = true;
                } else if (j - i == 1) {
                    // 两个字母
                    palindrome[i][j] = s[i] == s[j];
                } else {
                    // s[i+1...j-1] 前后添加 s[i] 和 s[j]
                    palindrome[i][j] = palindrome[i + 1][j - 1] && s[i] == s[j];
                }
            }
        }
    }
}
