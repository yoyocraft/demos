package org.example.algo.leetcode;

import org.example.algo.ModelParser;
import org.example.algo.OjAssertUtil;
import org.example.algo.constant.SymbolConstant;
import org.example.algo.model.TreeNode;

/**
 * @author yoyocraft
 * @date 2024/08/29
 */
public class LC114 {

    public static void main(String[] args) {
        LC114 lc114 = new LC114();
        OjAssertUtil.judgeResult((tc) -> {
            String[] inOut = tc.split(SymbolConstant.SPACE);
            String expect = inOut[1];
            TreeNode root = ModelParser.buildTree(inOut[0]);
            lc114.flatten(root);
            String actual = ModelParser.serializeTree(root);
            OjAssertUtil.assertEquals(expect, actual);
        }, "lc114");
    }

    public void flatten(TreeNode root) {
        if (root == null) {
            return;
        }

        // 将左子树挂载到右子树上
        TreeNode right = root.right;
        root.right = root.left;
        root.left = null;

        // 递归处理原先的左子树和右子树
        flatten(root.right);
        flatten(right);

        // 将原先的右子树挂载到展开的左子树末端
        TreeNode tmp = root;
        while (tmp.right != null) {
            tmp = tmp.right;
        }
        tmp.right = right;
    }
}
