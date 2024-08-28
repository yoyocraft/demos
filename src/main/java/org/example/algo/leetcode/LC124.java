package org.example.algo.leetcode;

import org.example.algo.ModelParser;
import org.example.algo.OjAssertUtil;
import org.example.algo.model.TreeNode;

/**
 * @author yoyocraft
 * @date 2024/08/28
 */
public class LC124 {

    public static void main(String[] args) {
        LC124 lc124 = new LC124();
        OjAssertUtil.judgeResult((tc) -> {
            String[] inOut = tc.split(" ");
            TreeNode root = ModelParser.buildTree(inOut[0]);
            int expect = Integer.parseInt(inOut[1]);
            int actual = lc124.maxPathSum(root);
            OjAssertUtil.assertEquals(expect, actual);
        }, "lc124");
    }


    int ans;

    public int maxPathSum(TreeNode root) {
        ans = Integer.MIN_VALUE;
        if (root == null) {
            return 0;
        }

        f(root);
        return ans;
    }

    int f(TreeNode node) {
        if (node == null) {
            return 0;
        }

        int lVal = Math.max(f(node.left), 0);
        int rVal = Math.max(f(node.right), 0);

        // 跨越 node 的路径
        ans = Math.max(ans, node.val + lVal + rVal);
        return Math.max(lVal, rVal) + node.val;
    }
}
