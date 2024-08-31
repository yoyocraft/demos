package org.example.algo;

import org.example.algo.constant.OjConstant;
import org.example.algo.constant.SymbolConstant;
import org.example.algo.model.ListNode;
import org.example.algo.model.TreeNode;

import java.util.Arrays;
import java.util.Deque;
import java.util.LinkedList;
import java.util.List;
import java.util.regex.Pattern;
import java.util.stream.Collectors;

/**
 * @author yoyocraft
 * @date 2024/08/28
 */
public class ModelParser {

    private static final Pattern ARRAY_2D_SPLIT_PATTERN = Pattern.compile(OjConstant.ARRAY_2D_SPLIT_PATTERN);

    /**
     * @param s [item, item, ...]
     * @return int[]
     */
    public static int[] parseIntArray(String s) {
        return Arrays.stream(s.substring(1, s.length() - 1).split(SymbolConstant.COMMA))
                .mapToInt(Integer::parseInt)
                .toArray();
    }

    /**
     * @param s [item, item, ...]
     * @return String[]
     */
    public static String[] parseStringArray(String s) {
        // bugfix [] 会转换成 [""]
        if (OjConstant.EMPTY_LIST.equals(s)) {
            return new String[0];
        }
        return Arrays.stream(s.substring(1, s.length() - 1).split(SymbolConstant.COMMA))
                .toArray(String[]::new);
    }

    /**
     * @param nums array
     * @return [item, item, ...]
     */
    public static String parseString(int[] nums) {
        return java.lang.String.format(OjConstant.ARRAY_TEMPLATE, Arrays.stream(nums)
                .mapToObj(java.lang.String::valueOf)
                .collect(Collectors.joining(SymbolConstant.COMMA)));
    }

    /**
     * @param list list
     * @return [...], [[...],[...],...], ...
     */
    public static String parseString(List<?> list) {
        if (list.isEmpty()) {
            return OjConstant.EMPTY_LIST;
        }

        return list.stream()
                .map(element -> {
                    if (element instanceof List) {
                        return parseString((List<?>) element);
                    } else {
                        return String.valueOf(element);
                    }
                })
                .collect(Collectors.joining(SymbolConstant.COMMA, SymbolConstant.LEFT_BRACKET, SymbolConstant.RIGHT_BRACKET));
    }

    /**
     * "[[...], [...]]" => int[][]
     *
     * @param s "[[...], [...]]"
     * @return int[][]
     */
    public static int[][] parseIntArray2D(String s) {
        String[] rows = ARRAY_2D_SPLIT_PATTERN.split(s.substring(2, s.length() - 2));
        return Arrays.stream(rows)
                .map(row -> Arrays.stream(row.split(SymbolConstant.COMMA))
                        .mapToInt(Integer::parseInt)
                        .toArray())
                .toArray(int[][]::new);
    }

    public static TreeNode buildTree(String s) {
        String[] nums = parseStringArray(s);
        if (nums.length == 0) {
            return null;
        }
        // 构造
        return buildTree(nums, 0);
    }

    public static String serializeTree(TreeNode root) {
        if (root == null) {
            return OjConstant.EMPTY_TREE;
        }

        StringBuilder serial = new StringBuilder(SymbolConstant.LEFT_BRACKET);
        Deque<TreeNode> que = new LinkedList<>();
        que.offer(root);
        while (!que.isEmpty()) {
            TreeNode node = que.poll();
            if (node != null) {
                serial.append(node.val).append(SymbolConstant.COMMA);
                que.offer(node.left);
                que.offer(node.right);
            } else {
                serial.append(OjConstant.NULL_VALUE).append(SymbolConstant.COMMA);
            }
        }
        // 删除最后一个逗号
        serial.deleteCharAt(serial.length() - 1);
        serial.append(SymbolConstant.RIGHT_BRACKET);
        return serial.toString();
    }

    public TreeNode deserializeTree(String data) {
        if (OjConstant.NULL_VALUE.equals(data)) {
            return null;
        }

        String[] vals = data.substring(1, data.length() - 1).split(SymbolConstant.COMMA);
        TreeNode root = new TreeNode(Integer.parseInt(vals[0]));
        Deque<TreeNode> que = new LinkedList<>();
        que.offer(root);
        int idx = 1;
        while (!que.isEmpty()) {
            TreeNode node = que.poll();
            if (!OjConstant.NULL_VALUE.equals(vals[idx])) {
                node.left = new TreeNode(Integer.parseInt(vals[idx]));
                que.offer(node.left);
            }
            idx++;
            if (!OjConstant.NULL_VALUE.equals(vals[idx])) {
                node.right = new TreeNode(Integer.parseInt(vals[idx]));
                que.offer(node.right);
            }
            idx++;
        }
        return root;
    }

    /**
     * [a,b,c] => a -> b -> c
     *
     * @param s [item, item, ...]
     * @return ListNode head node
     */
    public static ListNode buildList(String s) {
        int[] nums = parseIntArray(s);
        if (nums.length == 0) {
            return null;
        }

        ListNode dummy = new ListNode();
        ListNode cur = dummy;
        for (int num : nums) {
            cur.next = new ListNode(num);
            cur = cur.next;
        }
        return dummy.next;
    }

    /**
     * a -> b -> c => [a,b,c]
     *
     * @param head head node
     * @return [item, item, ...]
     */
    public static String serializeList(ListNode head) {
        if (head == null) {
            return OjConstant.EMPTY_LIST;
        }

        StringBuilder serial = new StringBuilder(SymbolConstant.LEFT_BRACKET);
        Deque<ListNode> que = new LinkedList<>();
        que.offer(head);
        while (!que.isEmpty()) {
            ListNode node = que.poll();
            if (node != null) {
                serial.append(node.val).append(SymbolConstant.COMMA);
                que.offer(node.next);
            }
        }
        return serial.deleteCharAt(serial.length() - 1).append(SymbolConstant.RIGHT_BRACKET).toString();
    }

    private static TreeNode buildTree(String[] nums, int idx) {
        if (idx >= nums.length || OjConstant.NULL_VALUE.equals(nums[idx])) {
            return null;
        }
        TreeNode root = new TreeNode(Integer.parseInt(nums[idx]));
        root.left = buildTree(nums, 2 * idx + 1);
        root.right = buildTree(nums, 2 * idx + 2);
        return root;
    }
}
