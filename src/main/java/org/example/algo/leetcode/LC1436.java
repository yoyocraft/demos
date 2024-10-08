package org.example.algo.leetcode;

import org.example.algo.ModelParser;
import org.example.algo.OjAssertUtil;
import org.example.algo.constant.SymbolConstant;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * @author yoyocraft
 * @date 2024/10/08
 */
public class LC1436 {

    public static void main(String[] args) {
        LC1436 lc1436 = new LC1436();
        OjAssertUtil.judgeResult((tc) -> {
            String[] inOut = tc.split(SymbolConstant.HASH);
            List<List<String>> paths = ModelParser.parseList2D(inOut[0], String.class);
            String expect = inOut[1];
            String actual = lc1436.destCity(paths);
            OjAssertUtil.assertEquals(expect, actual);
        }, "lc1436");
    }

    public String destCity(List<List<String>> paths) {
        Set<String> impossible = new HashSet<>(paths.size());
        Set<String> possible = new HashSet<>(paths.size());

        for (List<String> path : paths) {
            String from = path.get(0);
            String to = path.get(1);

            possible.remove(from);
            if (!impossible.contains(to)) {
                possible.add(to);
            }
            impossible.add(from);
        }
        return possible.iterator().next();
    }
}
