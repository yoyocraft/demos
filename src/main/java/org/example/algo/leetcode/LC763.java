package org.example.algo.leetcode;

import org.example.algo.ModelParser;
import org.example.algo.OjAssertUtil;
import org.example.algo.constant.SymbolConstant;

import java.util.ArrayList;
import java.util.List;

/**
 * @author yoyocraft
 * @date 2024/09/10
 */
public class LC763 {

    public static void main(String[] args) {
        LC763 lc763 = new LC763();
        OjAssertUtil.judgeResult((tc) -> {
            String[] inOut = tc.split(SymbolConstant.SPACE);
            String expect = inOut[1];
            List<Integer> actual = lc763.partitionLabels(inOut[0]);
            OjAssertUtil.assertEquals(expect, ModelParser.parseString(actual));
        }, "lc763");
    }


    public List<Integer> partitionLabels(String s) {
        int n = s.length();
        if (n == 1) {
            return new ArrayList<>();
        }

        int[] maxIdxPos = new int[26];
        for (int i = 0; i < n; i++) {
            // 统计每个字符的最大下标位置
            maxIdxPos[s.charAt(i) - 'a'] = i;
        }

        List<Integer> ans = new ArrayList<>();
        int maxIdx = 0;
        for (int l = -1, r = 0; r < n; r++) {
            maxIdx = Math.max(maxIdx, maxIdxPos[s.charAt(r) - 'a']);
            if (r == maxIdx) {
                ans.add(r - l);
                l = r;
            }
        }
        return ans;
    }
}
