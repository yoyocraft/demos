package org.example.algo.leetcode;

import org.example.algo.ModelParser;
import org.example.algo.OjAssertUtil;
import org.example.algo.constant.SymbolConstant;
import org.example.algo.model.TreeNode;

import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Deque;
import java.util.List;

/**
 * @author yoyocraft
 * @date 2024/09/28
 */
public class LC199 {

    public static void main(String[] args) {
        LC199 lc199 = new LC199();
        OjAssertUtil.judgeResult((tc) -> {
            String[] inOut = tc.split(SymbolConstant.SPACE);
            TreeNode root = ModelParser.buildTree(inOut[0]);
            String expect = inOut[1];
            List<Integer> actual = lc199.rightSideView(root);
            OjAssertUtil.assertEquals(expect, ModelParser.parseString(actual));
        }, "lc199");
    }

    public List<Integer> rightSideView(TreeNode root) {
        List<Integer> ans = new ArrayList<>();
        if (root == null) {
            return ans;
        }
        Deque<TreeNode> que = new ArrayDeque<>();
        que.add(root);
        while (!que.isEmpty()) {
            int size = que.size();
            for (int i = 0; i < size; i++) {
                TreeNode node = que.poll();
                if (i == size - 1) {
                    ans.add(node.val);
                }
                if (node.left != null) {
                    que.add(node.left);
                }
                if (node.right != null) {
                    que.add(node.right);
                }
            }
        }
        return ans;
    }

    public List<Integer> f1(TreeNode root) {
        List<Integer> ans = new ArrayList<>();
        if (root == null) {
            return ans;
        }

        f(root, 0, ans);
        return ans;
    }

    void f(TreeNode node, int depth, List<Integer> ans) {
        if (node == null) {
            return;
        }

        if (depth == ans.size()) {
            ans.add(node.val);
        }

        f(node.right, depth + 1, ans);
        f(node.left, depth + 1, ans);
    }

}
