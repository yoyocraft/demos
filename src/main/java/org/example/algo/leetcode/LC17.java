package org.example.algo.leetcode;

import org.example.algo.ModelParser;
import org.example.algo.OjAssertUtil;
import org.example.algo.constant.SymbolConstant;

import java.util.ArrayList;
import java.util.List;

/**
 * @author yoyocraft
 * @date 2024/10/09
 */
public class LC17 {
    public static void main(String[] args) {
        LC17 lc17 = new LC17();

        OjAssertUtil.judgeResult((tc) -> {
            String[] inOut = tc.split(SymbolConstant.UNDER_LINE);
            String digits = inOut[0];
            String expect = inOut[1];
            List<String> actual = lc17.letterCombinations(digits);
            OjAssertUtil.assertEquals(expect, ModelParser.parseString(actual));
        }, "lc17");
    }

    static final String[] LETTERS = {
            "",
            "", "abc", "def",
            "ghi", "jkl", "mno",
            "pqrs", "tuv", "wxyz"
    };

    public List<String> letterCombinations(String digits) {
        int n = digits.length();
        if (n == 0) {
            return new ArrayList<>();
        }

        List<String> ans = new ArrayList<>();
        f(digits.toCharArray(), 0, new char[n], ans);
        return ans;
    }

    void f(char[] digits, int i, char[] path, List<String> ans) {
        if (i == digits.length) {
            ans.add(new String(path));
            return;
        }

        char[] letters = LETTERS[digits[i] - '0'].toCharArray();
        for (char c : letters) {
            path[i] = c;
            f(digits, i + 1, path, ans);
        }
    }
}
