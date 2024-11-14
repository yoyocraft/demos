package org.example.algo.jz;

import org.example.algo.ModelParser;
import org.example.algo.OjAssertUtil;
import org.example.algo.TargetType;
import org.example.algo.constant.OjConstant;
import org.example.algo.constant.SymbolConstant;
import org.example.algo.model.TreeLinkNode;

import java.util.ArrayDeque;
import java.util.Deque;

/**
 * <a href="https://www.nowcoder.com/practice/9023a0c988684a53960365b889ceaf5e">JZ7</a>
 * <p>
 * 给定一个二叉树其中的一个结点，请找出中序遍历顺序的下一个结点并且返回。注意，树中的结点不仅包含左右子结点，同时包含指向父结点的next指针。
 * 数据范围：节点数满足 1≤n≤50 ，节点上的值满足 1≤val≤100
 * 要求：空间复杂度 O(1) ，时间复杂度 O(n)
 */
public class JZ7 {
    public static void main(String[] args) {
        JZ7 jz7 = new JZ7();
        OjAssertUtil.judgeResult((tc) -> {
            String[] inOut = tc.split(SymbolConstant.SPACE);
            TreeLinkNode root = ModelParser.buildLinkTree(inOut[0]);
            int val = Integer.parseInt(inOut[1]);
            TreeLinkNode pNode = findNode(root, val);
            String expect = inOut[2];
            TreeLinkNode actual = jz7.GetNext(pNode);
            OjAssertUtil.assertEquals(
                    expect,
                    actual == null ? OjConstant.NULL_VALUE : String.valueOf(actual.val)
            );
        }, "jz7", TargetType.JZ);
    }

    private static TreeLinkNode findNode(TreeLinkNode root, int val) {
        if (root == null) {
            return null;
        }
        TreeLinkNode cursor = root;
        Deque<TreeLinkNode> stk = new ArrayDeque<>();
        stk.push(cursor);
        while (cursor != null || !stk.isEmpty()) {
            while (cursor != null) {
                stk.push(cursor);
                cursor = cursor.left;
            }
            cursor = stk.pop();
            if (cursor.val == val) {
                return cursor;
            }
            cursor = cursor.right;
        }
        return null;
    }

    public TreeLinkNode GetNext(TreeLinkNode pNode) {
        if (pNode == null) {
            return null;
        }
        TreeLinkNode pNext = null;
        // 1. pNode 右子树不为空，下一个节点即为右子树的最左节点
        if (pNode.right != null) {
            TreeLinkNode right = pNode.right;
            while (right.left != null) {
                right = right.left;
            }
            pNext = right;
        }
        // 2. pNode 右子树为空
        // 2.1. pNode 是其父节点的左节点，下一个节点即为其父节点
        // 2.2. pNode 是其父节点的右节点，这种情况需要顺着父节点一路向上遍历
        //      直到找到一个节点，该节点是父节点的左节点，下一个节点即为该节点的父节点
        else if (pNode.next != null) {
            TreeLinkNode curr = pNode, parent = pNode.next;
            while (parent != null && curr == parent.right) {
                curr = parent;
                parent = parent.next;
            }
            pNext = parent;
        }
        return pNext;
    }
}
