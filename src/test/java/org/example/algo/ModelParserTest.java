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
        String s = "[1,3,2,5,null,null,9,6,null,7,null]";
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

    @Test
    public void test_parseIntArray2D() {
        String s = "[[2,1,1],[1,1,0],[0,1,1]]";
        int[][] nums = ModelParser.parseIntArray2D(s);
        assert Arrays.deepEquals(nums, new int[][]{{2, 1, 1}, {1, 1, 0}, {0, 1, 1}});
    }
}
