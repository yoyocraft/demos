package org.example.algo;

import org.example.algo.constant.OjConstant;
import org.example.algo.constant.SymbolConstant;
import org.example.algo.model.ListNode;
import org.example.algo.model.TreeLinkNode;
import org.example.algo.model.TreeNode;

import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Deque;
import java.util.LinkedList;
import java.util.List;
import java.util.Set;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.stream.Collectors;

/**
 * @author yoyocraft
 * @date 2024/08/28
 */
public class ModelParser {

    private static final Pattern ARRAY_2D_SPLIT_PATTERN = Pattern.compile(OjConstant.ARRAY_2D_SPLIT_PATTERN);
    private static final Pattern LIST_PATTERN = Pattern.compile(OjConstant.LIST_PATTERN);


    /**
     * @param s [item, item, ...]
     * @return int[]
     */
    public static int[] parseIntArray(String s) {
        if (s.length() <= 2) {
            return new int[0];
        }
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
        if (nums == null || nums.length == 0) {
            return OjConstant.EMPTY_LIST;
        }
        return String.format(
                OjConstant.ARRAY_TEMPLATE,
                Arrays.stream(nums)
                        .mapToObj(String::valueOf)
                        .collect(Collectors.joining(SymbolConstant.COMMA))
        );
    }

    /**
     * @param nums 2d array
     * @return [[item, item],[item],...]
     */
    public static String parseString(int[][] nums) {
        if (nums == null || nums.length == 0) {
            return OjConstant.EMPTY_LIST;
        }
        return Arrays.stream(nums)
                .map(ModelParser::parseString)
                .collect(Collectors.joining(SymbolConstant.COMMA, SymbolConstant.LEFT_BRACKET, SymbolConstant.RIGHT_BRACKET));
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
        // [[...],[...],...] => ...],[...],[...
        String[] rows = ARRAY_2D_SPLIT_PATTERN.split(s.substring(2, s.length() - 2));
        return Arrays.stream(rows)
                .map(row -> Arrays.stream(row.split(SymbolConstant.COMMA))
                        .mapToInt(Integer::parseInt)
                        .toArray())
                .toArray(int[][]::new);
    }

    /**
     * "[[...], [...]]" => char[][]
     *
     * @param s "[[...], [...]]"
     * @return int[][]
     */
    public static char[][] parseCharacterArray2D(String s) {
        // [[...],[...],...] => ...],[...],[...
        String[] rows = ARRAY_2D_SPLIT_PATTERN.split(s.substring(2, s.length() - 2));
        return Arrays.stream(rows)
                // 去掉行中的无效字符（空格、方括号、逗号等）
                .map(row -> row.replaceAll("[\\[\\]\\s,\"]", ""))
                .map(String::toCharArray)
                .toArray(char[][]::new);
    }

    /**
     * "[...]" => List
     *
     * @param s "[...]"
     * @return List
     */
    public static <T> List<T> parseList(String s, Class<T> type) {
        return Arrays.stream(parseStringArray(s))
                .map(element -> castToType(element, type))
                .collect(Collectors.toList());
    }

    /**
     * "[...]" => Set
     *
     * @param s "[...]"
     * @return Set
     */
    public static <T> Set<T> parseSet(String s, Class<T> type) {
        return Arrays.stream(parseStringArray(s))
                .map(element -> castToType(element, type))
                .collect(Collectors.toSet());
    }

    /**
     * "[[...], [...]]" => List
     *
     * @param s "[[...], [...]]"
     * @return List
     */
    public static <T> List<List<T>> parseList2D(String s, Class<T> type) {
        List<List<T>> result = new ArrayList<>();

        Matcher listMatcher = LIST_PATTERN.matcher(s);

        while (listMatcher.find()) {
            // Extract each inner list string (e.g., "1, 2")
            String listString = listMatcher.group(1);
            // Split the elements by commas
            String[] elements = listString.split(",");

            List<T> innerList = new ArrayList<>();
            for (String element : elements) {
                element = element.trim();
                T value = castToType(element, type);
                innerList.add(value);
            }

            result.add(innerList);
        }

        return result;
    }

    public static TreeNode buildTree(String s) {
        String[] nums = parseStringArray(s);
        if (nums.length == 0) {
            return null;
        }
        return buildTree(nums);
    }

    public static TreeLinkNode buildLinkTree(String s) {
        String[] nums = parseStringArray(s);
        if (nums.length == 0) {
            return null;
        }
        return buildTreeLink(nums);
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
                // 仅当 node 不是叶子节点时才加入其子节点
                if (node.left != null || node.right != null) {
                    que.offer(node.left);
                    que.offer(node.right);
                }
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
    public static String parseString(ListNode head) {
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

    private static TreeNode buildTree(String[] nums) {
        if (nums == null || nums.length == 0 || OjConstant.NULL_VALUE.equals(nums[0])) {
            return null;
        }

        int idx = 0, n = nums.length;
        TreeNode root = new TreeNode(Integer.parseInt(nums[idx]));
        Deque<TreeNode> nodeQue = new ArrayDeque<>();
        nodeQue.offer(root);
        while (!nodeQue.isEmpty()) {
            TreeNode node = nodeQue.poll();
            if (++idx < n && !OjConstant.NULL_VALUE.equals(nums[idx])) {
                node.left = new TreeNode(Integer.parseInt(nums[idx]));
                nodeQue.offer(node.left);
            }
            if (++idx < n && !OjConstant.NULL_VALUE.equals(nums[idx])) {
                node.right = new TreeNode(Integer.parseInt(nums[idx]));
                nodeQue.offer(node.right);
            }
        }
        return root;
    }

    private static TreeLinkNode buildTreeLink(String[] nums) {
        if (nums == null || nums.length == 0 || OjConstant.NULL_VALUE.equals(nums[0])) {
            return null;
        }
        int idx = 0, n = nums.length;
        TreeLinkNode root = new TreeLinkNode(Integer.parseInt(nums[idx]));
        Deque<TreeLinkNode> nodeQue = new ArrayDeque<>();
        nodeQue.offer(root);
        while (!nodeQue.isEmpty()) {
            TreeLinkNode node = nodeQue.poll();

            // 在当前节点上，设置其子节点的父节点指向自己
            if (++idx < n && !OjConstant.NULL_VALUE.equals(nums[idx])) {
                node.left = new TreeLinkNode(Integer.parseInt(nums[idx]));
                node.left.next = node;
                nodeQue.offer(node.left);
            }
            if (++idx < n && !OjConstant.NULL_VALUE.equals(nums[idx])) {
                node.right = new TreeLinkNode(Integer.parseInt(nums[idx]));
                node.right.next = node;
                nodeQue.offer(node.right);
            }
        }
        return root;
    }


    @SuppressWarnings("unchecked")
    private static <T> T castToType(String value, Class<T> type) {
        if (type == Integer.class) {
            return (T) Integer.valueOf(value);
        } else if (type == Double.class) {
            return (T) Double.valueOf(value);
        } else if (type == String.class) {
            return (T) value;
        } else {
            throw new IllegalArgumentException("Unsupported type: " + type.getName());
        }
    }

    /**
     * @see ModelParser#buildTree(String[])
     * @deprecated
     */
    @Deprecated
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
