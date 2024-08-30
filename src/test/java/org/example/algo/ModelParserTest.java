package org.example.algo;

import org.example.algo.model.ListNode;
import org.example.algo.model.TreeNode;
import org.junit.Test;

import java.util.Arrays;
import java.util.List;

/**
 * @author yoyocraft
 * @date 2024/08/30
 */
public class ModelParserTest {
    @Test
    public void test_parserString_List() {
        List<Integer> list = Arrays.asList(1, 2, 3);
        String result = ModelParser.parseString(list);
        assert "[1,2,3]".equals(result);
        List<List<Integer>> listList = Arrays.asList(Arrays.asList(1, 2), Arrays.asList(3, 4));
        assert "[[1,2],[3,4]]".equals(ModelParser.parseString(listList));
    }

    @Test
    public void test_buildTree() {
        String s = "[-10,9,20,null,null,15,7]";
        TreeNode root = ModelParser.buildTree(s);
        String serialized = ModelParser.serializeTree(root);
        System.out.println("serialized = " + serialized);
    }

    @Test
    public void test_buildList() {
        String s = "[1,2,3,4,5,6,7]";
        ListNode head = ModelParser.buildList(s);
        String serializedList = ModelParser.serializeList(head);
        assert s.equals(serializedList);
    }
}
