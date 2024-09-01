package org.example.algo.test;

import org.example.algo.ModelParser;
import org.example.algo.OjAssertUtil;
import org.example.algo.constant.SymbolConstant;

import java.util.Arrays;

/**
 * @author yoyocraft
 * @date 2024/09/01
 */
public class Test_01 {
    public static void main(String[] args) {
        // 两个数组a和b，从他们中各取一个数，两数之和≥target总共多少种组合
        Test_01 test01 = new Test_01();
        OjAssertUtil.judgeResult((tc) -> {
            String[] inOut = tc.split(SymbolConstant.SPACE);
            int[] a = ModelParser.parseIntArray(inOut[0]);
            int[] b = ModelParser.parseIntArray(inOut[1]);
            int target = Integer.parseInt(inOut[2]);
            int expect = Integer.parseInt(inOut[3]);
            int actual = test01.getCount(a, b, target);
            OjAssertUtil.assertEquals(expect, actual);
        }, "test_01");
    }

    private int getCount(int[] a, int[] b, int target) {
        Arrays.sort(a);
        Arrays.sort(b);
        int ans = 0;
        for (int x : a) {
            int l = 0, r = b.length - 1;
            while (l <= r) {
                int mid = l + ((r - l) >> 1);
                if (x + b[mid] >= target) {
                    r = mid - 1;
                } else {
                    l = mid + 1;
                }
            }
            ans += b.length - l;
        }
        return ans;
    }


}
