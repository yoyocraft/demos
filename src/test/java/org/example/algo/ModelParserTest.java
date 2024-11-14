package org.example.algo;

import cn.hutool.json.JSONUtil;
import com.google.common.base.Preconditions;
import com.google.common.base.Predicates;
import org.example.algo.model.ListNode;
import org.example.algo.model.TreeLinkNode;
import org.junit.Test;

import java.util.ArrayDeque;
import java.util.Arrays;
import java.util.Deque;
import java.util.List;
import java.util.Optional;

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

        List<List<String>> stringList = Arrays.asList(
                Arrays.asList(".Q..", "...Q", "Q...", "..Q."),
                Arrays.asList("..Q.", "Q...", "...Q", ".Q..")
        );
        assert "[[.Q..,...Q,Q...,..Q.],[..Q.,Q...,...Q,.Q..]]".equals(ModelParser.parseString(stringList));
    }

    @Test
    public void test_buildList() {
        String s = "[1,2,3,4,5,6,7]";
        ListNode head = ModelParser.buildList(s);
        String serializedList = ModelParser.parseString(head);
        assert s.equals(serializedList);
    }

    @Test
    public void test_parseIntArray2D() {
        String s = "[[2,1,1],[1,1,0],[0,1,1]]";
        int[][] nums = ModelParser.parseIntArray2D(s);
        assert Arrays.deepEquals(nums, new int[][]{{2, 1, 1}, {1, 1, 0}, {0, 1, 1}});
    }

    @Test
    public void test_parserString_2D_Array() {
        int[][] nums = new int[][]{{1, 2, 3}, {4, 5, 6}};
        assert "[[1,2,3],[4,5,6]]".equals(ModelParser.parseString(nums));
    }

    @Test
    public void test_parseString_1D_Array() {
        int[] nums = {-1, -1};
        assert "[-1,-1]".equals(ModelParser.parseString(nums));
    }

    @Test
    public void test_parseCharacterArray2D() {
        String s = "[[A,B,C,E],[S,F,C,S],[A,D,E,E]]";
        char[][] chars = ModelParser.parseCharacterArray2D(s);
        assert Arrays.deepEquals(chars, new char[][]{{'A', 'B', 'C', 'E'}, {'S', 'F', 'C', 'S'}, {'A', 'D', 'E', 'E'}});
    }

    @Test
    public void test_parseList2D() {
        String s = "[[London,New York],[New York,Lima],[Lima,Sao Paulo]]";
        List<List<String>> lists = ModelParser.parseList2D(s, String.class);
        Preconditions.checkNotNull(lists);
        Preconditions.checkArgument(lists.stream().allMatch(Predicates.notNull()));
    }

    @Test
    public void test_buildLinkTree() {
        String formatPattern = "val: %s, left: %s, right: %s, next: %s%n";
        String s = "[8,6,10,5,7,9,11]";
        TreeLinkNode root = ModelParser.buildLinkTree(s);
        TreeLinkNode cursor = root;
        Deque<TreeLinkNode> nodeQue = new ArrayDeque<>();
        nodeQue.offer(cursor);
        while (!nodeQue.isEmpty()) {
            cursor = nodeQue.poll();
            System.out.printf(
                    formatPattern,
                    cursor.val,
                    Optional.ofNullable(cursor.left).orElseGet(() -> new TreeLinkNode(0)).val,
                    Optional.ofNullable(cursor.right).orElseGet(() -> new TreeLinkNode(0)).val,
                    Optional.ofNullable(cursor.next).orElseGet(() -> new TreeLinkNode(0)).val
            );
            if (cursor.left != null) {
                nodeQue.offer(cursor.left);
            }
            if (cursor.right != null) {
                nodeQue.offer(cursor.right);
            }
        }
    }
}
