package org.example.algo.jz;

import org.example.algo.ModelParser;
import org.example.algo.OjAssertUtil;
import org.example.algo.TargetType;
import org.example.algo.constant.SymbolConstant;
import org.example.algo.model.TreeNode;

import java.util.HashMap;
import java.util.Map;

/**
 * <a href="https://www.nowcoder.com/practice/8a19cbe657394eeaac2f6ea9b0f6fcf6">JZ6</a>
 * <p>
 * 给定节点数为 n 的二叉树的前序遍历和中序遍历结果，请重建出该二叉树并返回它的头结点。
 * 数据范围：n <= 2000, -1000 <= val <= 1000
 * 要求：空间复杂度 O(n), 时间复杂度 O(n)
 */
public class JZ6 {
    public static void main(String[] args) {
        JZ6 jz6 = new JZ6();
        OjAssertUtil.judgeResult((tc) -> {
            String[] inOut = tc.split(SymbolConstant.SPACE);
            int[] preOrder = ModelParser.parseIntArray(inOut[0]);
            int[] vinOrder = ModelParser.parseIntArray(inOut[1]);
            String expect = inOut[2];
            TreeNode actual = jz6.reConstructBinaryTree(preOrder, vinOrder);
            OjAssertUtil.assertEquals(expect, ModelParser.serializeTree(actual));
        }, "jz6", TargetType.JZ);
    }


    public TreeNode reConstructBinaryTree(int[] preOrder, int[] vinOrder) {
        if (preOrder == null || preOrder.length == 0) {
            return null;
        }

        int n = preOrder.length;
        Map<Integer, Integer> vinOrderIdxMap = new HashMap<>(n);
        for (int i = 0; i < n; i++) {
            vinOrderIdxMap.put(vinOrder[i], i);
        }
        return build(preOrder, 0, n, vinOrderIdxMap, 0, n);
    }

    /**
     * [ps, pe), [vs, ve)
     * @param preOrder pre order array
     * @param ps pre order start index
     * @param pe pre order end index
     * @param vinOrderIdxMap vin val <=> idx
     * @param vs vin order start index
     * @param ve vin order end index
     * @return root node
     */
    TreeNode build(int[] preOrder, int ps, int pe, Map<Integer, Integer> vinOrderIdxMap, int vs, int ve) {
        if (ps >= pe || vs >= ve) {
            return null;
        }

        int rootVal = preOrder[ps];
        int vinRootIdx = vinOrderIdxMap.get(rootVal);

        TreeNode root = new TreeNode(rootVal);
        int leftTreeSize = vinRootIdx - vs;
        root.left = build(preOrder, ps + 1, ps + 1 + leftTreeSize, vinOrderIdxMap, vs, vinRootIdx);
        root.right = build(preOrder, ps + 1 + leftTreeSize, pe, vinOrderIdxMap, vinRootIdx + 1, ve);
        return root;
    }
}
