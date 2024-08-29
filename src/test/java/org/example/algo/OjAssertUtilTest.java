package org.example.algo;

import org.example.algo.model.TreeNode;
import org.junit.Test;

import java.util.Arrays;
import java.util.List;

/**
 * @author yoyocraft
 * @date 2024/08/26
 */
public class OjAssertUtilTest {

    @Test
    public void test_parserString_List() {
        List<Integer> list = Arrays.asList(1, 2, 3);
        String result = ModelParser.parseString(list);
        System.out.println(result);

        List<List<Integer>> listList = Arrays.asList(Arrays.asList(1, 2), Arrays.asList(3, 4));
        System.out.println(ModelParser.parseString(listList));
    }

    @Test
    public void test_buildTree() {
        String s = "[-10,9,20,null,null,15,7]";
        TreeNode root = ModelParser.buildTree(s);
        prePrint(root);
    }

    void prePrint(TreeNode node) {
        if (node == null) {
            return;
        }

        System.out.println(node.val);
        prePrint(node.left);
        prePrint(node.right);
    }
}
