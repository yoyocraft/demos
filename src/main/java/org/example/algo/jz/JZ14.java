package org.example.algo.jz;

import org.example.algo.ModelParser;
import org.example.algo.OjAssertUtil;
import org.example.algo.TargetType;
import org.example.algo.constant.SymbolConstant;

import java.util.Arrays;

/**
 * <a href="https://www.nowcoder.com/practice/389fc1c3d3be4479a154f63f495abff8">JZ14</a>
 * <p>
 * 一个整型数组里除了两个数字只出现一次，其他的数字都出现了两次。请写程序找出这两个只出现一次的数字。
 * 数据范围：数组长度 2 <= n <= 1000, 数组中每个数的大小 0 <= val <= 1000000
 * 要求：空间复杂度 O(1), 时间复杂度 O(n), 返回的结果中较小的数排在前面
 */
public class JZ14 {

    public static void main(String[] args) {
        JZ14 jz14 = new JZ14();
        OjAssertUtil.judgeResult((tc) -> {
            String[] inOut = tc.split(SymbolConstant.SPACE);
            int[] nums = ModelParser.parseIntArray(inOut[0]);
            String expect = inOut[1];
            int[] actual = jz14.FindNumsAppearOnce(nums);
            OjAssertUtil.assertEquals(expect, ModelParser.parseString(actual));
        }, "jz14", TargetType.JZ);
    }

    public int[] FindNumsAppearOnce(int[] nums) {
        if (nums == null || nums.length == 0) {
            return new int[0];
        }
        // 1. 得到整个数组的异或和，这个异或和只能是两个不同元素的异或和
        int xor = Arrays.stream(nums)
                .reduce(0, (acc, num) -> acc ^ num);
        // 2. 根据异或和，对数组元素进行分组
        int mask = 1;
        while ((xor & mask) == 0) {
            mask <<= 1;
        }
        // 3. 对分组之后的元素分别进行异或运算
        int a = 0;
        int b = 0;
        for (int x : nums) {
            if ((x & mask) == 0) {
                a ^= x;
            } else {
                b ^= x;
            }
        }
        // 4. 整理得到结果
        if (a > b) {
            a ^= b;
            b ^= a;
            a ^= b;
        }
        return new int[]{a, b};
    }
}
