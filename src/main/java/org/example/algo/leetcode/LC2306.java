package org.example.algo.leetcode;

import org.example.algo.ModelParser;
import org.example.algo.OjAssertUtil;
import org.example.algo.constant.SymbolConstant;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

/**
 * @author yoyocraft
 * @date 2024/09/25
 */
public class LC2306 {
    public static void main(String[] args) {
        LC2306 lc2306 = new LC2306();
        OjAssertUtil.judgeResult((tc) -> {
            String[] inOut = tc.split(SymbolConstant.SPACE);
            String[] ideas = ModelParser.parseStringArray(inOut[0]);
            long expect = Long.parseLong(inOut[1]);
            long actual = lc2306.distinctNames(ideas);
            OjAssertUtil.assertEquals(expect, actual);
        }, "lc2306");
    }

    public long distinctNames(String[] ideas) {
        Set<String>[] groups = new HashSet[26];
        Arrays.setAll(groups, i -> new HashSet<>());
        for (String idea : ideas) {
            // 按照首字母分组
            groups[idea.charAt(0) - 'a'].add(idea.substring(1));
        }

        long ans = 0;
        for (int i = 1; i < 26; i++) {
            for (int j = 0; j < i; j++) {
                // groups[i] 和 groups[j] 交集的大小
                long count = groups[i].stream().filter(groups[j]::contains).count();
                ans += (groups[i].size() - count) * (groups[j].size() - count);
            }
        }
        return ans * 2;
    }
}
