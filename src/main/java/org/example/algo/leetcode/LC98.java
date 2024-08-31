package org.example.algo.leetcode;

import org.example.algo.ModelParser;
import org.example.algo.OjAssertUtil;
import org.example.algo.constant.SymbolConstant;
import org.example.algo.model.TreeNode;

import java.util.ArrayDeque;
import java.util.Deque;

/**
 * @author yoyocraft
 * @date 2024/08/31
 */
public class LC98 {

    public static void main(String[] args) {
        LC98 lc98 = new LC98();
        OjAssertUtil.judgeResult((tc) -> {
            String[] inOut = tc.split(SymbolConstant.SPACE);
            TreeNode root = ModelParser.buildTree(inOut[0]);
            boolean expect = Boolean.parseBoolean(inOut[1]);
            boolean actual = lc98.isValidBST(root);
            OjAssertUtil.assertEquals(expect, actual);
        }, "lc98");
    }

    TreeNode pre;

    public boolean isValidBST(TreeNode root) {
        if (root == null) {
            return true;
        }

        return f(root, Long.MIN_VALUE, Long.MAX_VALUE);
    }

    boolean f(TreeNode node, long left, long right) {
        if (node == null) {
            return true;
        }

        if (node.val >= right || node.val <= left) {
            return false;
        }
        return f(node.left, left, node.val) && f(node.right, node.val, right);
    }

    private boolean f2(TreeNode root) {
        if (root == null) {
            return true;
        }
        Deque<TreeNode> st = new ArrayDeque<>();
        while (root != null || !st.isEmpty()) {
            while (root != null) {
                st.push(root);
                root = root.left;
            }
            root = st.pop();
            if (pre != null && pre.val >= root.val) {
                return false;
            }
            pre = root;
            root = root.right;
        }
        return true;
    }

    private boolean f1(TreeNode root) {
        if (root == null) {
            return true;
        }

        boolean left = isValidBST(root.left);
        if (pre != null && pre.val >= root.val) {
            return false;
        }
        pre = root;

        return left && isValidBST(root.right);
    }


}
