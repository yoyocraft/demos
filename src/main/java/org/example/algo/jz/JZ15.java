package org.example.algo.jz;

import org.example.algo.ModelParser;
import org.example.algo.OjAssertUtil;
import org.example.algo.TargetType;
import org.example.algo.constant.SymbolConstant;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.PriorityQueue;
import java.util.Queue;

/**
 * <a href="https://www.nowcoder.com/practice/6a296eb82cf844ca8539b57c23e6e9bf">JZ15</a>
 * <p>
 * 给定一个长度为 n 的可能有重复值的数组，找出其中不去重的最小的 k 个数。
 * 例如数组元素是4,5,1,6,2,7,3,8这8个数字，则最小的4个数字是1,2,3,4(任意顺序皆可)。
 * 数据范围: 0 <= k, n <= 10000, 数组中每个数的大小 0 <= val <= 1000
 * 要求：空间复杂度 O(n), 时间复杂度 O(nlog_k)
 */
public class JZ15 {
    public static void main(String[] args) {
        JZ15 jz15 = new JZ15();
        OjAssertUtil.judgeResult((tc) -> {
            String[] inOut = tc.split(SymbolConstant.SPACE);
            int[] input = ModelParser.parseIntArray(inOut[0]);
            int k = Integer.parseInt(inOut[1]);
            String expect = inOut[2];
            ArrayList<Integer> actual = jz15.GetLeastNumbers_Solution(input, k);
            Collections.sort(actual);
            OjAssertUtil.assertEquals(expect, ModelParser.parseString(actual));
        }, "jz15", TargetType.JZ);
    }

    public ArrayList<Integer> GetLeastNumbers_Solution(int[] input, int k) {
        if (k == 0 || input == null || input.length == 0 || k > input.length) {
            return new ArrayList<>(0);
        }
        ArrayList<Integer> ans = new ArrayList<>(k);
        quickSort(input, k, 0, input.length - 1, ans);
        return ans;
    }

    void quickSort(int[] input, int k, int l, int r, ArrayList<Integer> ans) {
        int start = l, end = r;
        while (l < r) {
            while (l < r && input[r] >= input[start]) {
                r--;
            }
            while (l < r && input[l] <= input[start]) {
                l++;
            }
            swap(input, l, r);
        }
        swap(input, l, start);
        if (l > k) {
            quickSort(input, k, start, l - 1, ans);
        } else if (l < k) {
            quickSort(input, k, l + 1, end, ans);
        } else {
            for (int i = 0; i < k; i++) {
                ans.add(input[i]);
            }
        }
    }

    void swap(int[] nums, int i, int j) {
        if (i == j) {
            return;
        }
        int tmp = nums[i];
        nums[i] = nums[j];
        nums[j] = tmp;
    }

    public ArrayList<Integer> f1(int[] input, int k) {
        if (k == 0 || input == null || input.length == 0) {
            return new ArrayList<>(0);
        }

        ArrayList<Integer> ans = new ArrayList<>(k);
        // 大顶堆
        Queue<Integer> pq = new PriorityQueue<>(Comparator.reverseOrder());
        for (int i = 0; i < k; i++) {
            pq.add(input[i]);
        }
        for (int i = k; i < input.length; i++) {
            if (pq.isEmpty()) {
                pq.add(input[i]);
            } else if (pq.peek() > input[i]) {
                pq.poll();
                pq.add(input[i]);
            }
        }
        while (!pq.isEmpty()) {
            ans.add(pq.poll());
        }
        return ans;
    }
}
