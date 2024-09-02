package org.example.algo.leetcode;

import org.example.algo.ModelParser;
import org.example.algo.OjAssertUtil;
import org.example.algo.constant.SymbolConstant;
import org.example.algo.model.TreeNode;

/**
 * @author yoyocraft
 * @date 2024/09/02
 */
public class LC662 {

    public static void main(String[] args) {
        LC662 lc662 = new LC662();
        OjAssertUtil.judgeResult((tc) -> {
            String[] inOut = tc.split(SymbolConstant.SPACE);
            TreeNode root = ModelParser.buildTree(inOut[0]);
            int expect = Integer.parseInt(inOut[1]);
            int actual = lc662.widthOfBinaryTree(root);
            OjAssertUtil.assertEquals(expect, actual);
        }, "lc662");
    }


    public static final int MAX_N = 3001;
    public static TreeNode[] nodeQue = new TreeNode[MAX_N];
    public static int[] idQue = new int[MAX_N];
    public static int l, r;

    public int widthOfBinaryTree(TreeNode root) {
        if (root == null) {
            return 0;
        }
        // root 宽度至少是 1
        int ans = 1;
        // init
        l = r = 0;
        nodeQue[r] = root;
        idQue[r] = 1;
        r++;

        while (l < r) {
            int size = r - l;
            ans = Math.max(ans, idQue[r - 1] - idQue[l] + 1);
            for (int i = 0; i < size; i++) {
                TreeNode node = nodeQue[l];
                int id = idQue[l];
                l++;
                if (node.left != null) {
                    nodeQue[r] = node.left;
                    idQue[r] = id * 2;
                    r++;
                }
                if (node.right != null) {
                    nodeQue[r] = node.right;
                    idQue[r] = id * 2 + 1;
                    r++;
                }
            }
        }
        return ans;
    }
}
