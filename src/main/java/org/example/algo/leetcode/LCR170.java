package org.example.algo.leetcode;

import org.example.algo.ModelParser;
import org.example.algo.OjAssertUtil;
import org.example.algo.constant.SymbolConstant;

/**
 * @author <a href="https://github.com/yoyocraft">yoyocraft</a>
 * @date 2025/05/08
 */
public class LCR170 {

    public static void main(String[] args) {
        LCR170 lcr170 = new LCR170();
        OjAssertUtil.judgeResult((tc) -> {
            String[] inOut = tc.split(SymbolConstant.SPACE);
            int[] record = ModelParser.parseIntArray(inOut[0]);
            int expect = Integer.parseInt(inOut[1]);
            int actual = lcr170.reversePairs(record);
            OjAssertUtil.assertEquals(expect, actual);
        }, "lcr170");
    }

    public int reversePairs(int[] record) {
        if (record == null || record.length < 2) {
            return 0;
        }
        int n = record.length;
        int[] recordBak = new int[n];
        System.arraycopy(record, 0, recordBak, 0, n);
        int[] tmp = new int[n];
        return reversePairs(recordBak, 0, n - 1, tmp);
    }

    // 对 nums[left..right] 计算逆序对个数并且排序
    private int reversePairs(int[] nums, int left, int right, int[] tmp) {
        if (left == right) {
            return 0;
        }
        int mid = left + (right - left) / 2;
        int leftPairs = reversePairs(nums, left, mid, tmp);
        int rightPairs = reversePairs(nums, mid + 1, right, tmp);

        // 数组有序，无需再进行合并
        if (nums[mid] <= nums[mid + 1]) {
            return leftPairs + rightPairs;
        }

        int crossPairs = mergeAndCount(nums, left, mid, right, tmp);
        return crossPairs + leftPairs + rightPairs;
    }

    // 归并排序并合并
    private int mergeAndCount(int[] nums, int left, int mid, int right, int[] tmp) {
        System.arraycopy(nums, left, tmp, left, right - left + 1);

        int i = left;
        int j = mid + 1;
        int reversePairCount = 0;
        for (int k = left; k <= right; k++) {
            // 左侧已经排序完成
            if (i == mid + 1) {
                nums[k] = tmp[j];
                j++;
            }
            // 右侧已经排序完成
            else if (j == right + 1) {
                nums[k] = tmp[i];
                i++;
            }
            // 注意是 <=
            else if (tmp[i] <= tmp[j]) {
                nums[k] = tmp[i];
                i++;
            } else {
                nums[k] = tmp[j];
                j++;
                // 上一个 elif 是 <= 的原因是逆序对的要求是 prev > next
                // 也就是说需要保证的是在计算逆序对时, tmp[i] > tmp[j]
                reversePairCount += (mid - i + 1);
            }
        }
        return reversePairCount;
    }
}
