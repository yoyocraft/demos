package org.example.algo.leetcode;

import org.example.algo.ModelParser;
import org.example.algo.OjAssertUtil;
import org.example.algo.constant.SymbolConstant;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author yoyocraft
 * @date 2024/09/26
 */
public class LC49 {

    public static void main(String[] args) {
        LC49 lc49 = new LC49();
        OjAssertUtil.judgeResult((tc) -> {
            String[] inOut = tc.split(SymbolConstant.SPACE);
            String[] strs = ModelParser.parseStringArray(inOut[0]);
            String expect = inOut[1];
            List<List<String>> actual = lc49.groupAnagrams(strs);
            OjAssertUtil.assertEquals(expect, ModelParser.parseString(actual));
        }, "lc49");
    }

    public List<List<String>> groupAnagrams(String[] strs) {
        Map<String, List<String>> map = new HashMap<>();
        for (String s : strs) {
            char[] ss = s.toCharArray();
            Arrays.sort(ss);
            map.computeIfAbsent(new String(ss), k -> new ArrayList<>()).add(s);
        }
        return new ArrayList<>(map.values());
    }

}
