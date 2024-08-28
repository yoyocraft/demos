package org.example.algo;

import org.example.algo.constant.OjConstant;
import org.example.algo.constant.SymbolConstant;
import org.example.algo.model.TreeNode;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

/**
 * @author yoyocraft
 * @date 2024/08/28
 */
public class ModelParser {
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

    public static TreeNode buildTree(String s) {
        String[] nums = parseStringArray(s);
        if (nums.length == 0) {
            return null;
        }
        // 构造
        return buildTree(nums, 0);
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
