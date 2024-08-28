package org.example.algo.leetcode;

import org.example.algo.ModelParser;
import org.example.algo.OjAssertUtil;

/**
 * @author yoyocraft
 * @date 2024/08/24
 */
public class LC11 {
    public static void main(String[] args) {
        LC11 lc11 = new LC11();
        OjAssertUtil.judgeResult((tc) -> {
            String[] inOut = tc.split(" ");
            String inArr = inOut[0];
            int actual = lc11.maxArea(ModelParser.parseIntArray(inArr));
            OjAssertUtil.assertEquals(Integer.parseInt(inOut[1]), actual);
        }, "lc11");
    }

    public int maxArea(int[] height) {
        if (height == null || height.length == 0) {
            return 0;
        }
        int n = height.length;
        int l = 0, r = n - 1;
        int ans = 0, area;
        while (l < r) {
            int lh = height[l], rh = height[r];
            int h = Math.min(lh, rh);
            area = h * (r - l);
            ans = Math.max(ans, area);

            if (lh < rh) {
                while (l < r && height[l] <= lh) {
                    l++;
                }
            } else {
                while (l < r && height[r] <= rh) {
                    r--;
                }
            }
        }
        return ans;
    }
}
