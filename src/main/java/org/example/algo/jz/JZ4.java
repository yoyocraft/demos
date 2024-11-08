package org.example.algo.jz;

import org.example.algo.ModelParser;
import org.example.algo.OjAssertUtil;
import org.example.algo.TargetType;
import org.example.algo.constant.SymbolConstant;

import java.util.Set;

/**
 * <a href="https://www.nowcoder.com/practice/6fe361ede7e54db1b84adc81d09d8524">JZ4</a>
 * <p>
 * 在一个长度为n的数组里的所有数字都在0到n-1的范围内。
 * 数组中某些数字是重复的，但不知道有几个数字是重复的。
 * 也不知道每个数字重复几次。请找出数组中任意一个重复的数字。
 * 例如，如果输入长度为7的数组[2,3,1,0,2,5,3]，那么对应的输出是2或者3。存在不合法的输入的话输出-1
 * 数据范围：0 <= n <= 1000
 * 进阶：时间复杂度 O(n)，空间复杂度 O(n)
 */
public class JZ4 {

    public static void main(String[] args) {
        JZ4 jz4 = new JZ4();
        OjAssertUtil.judgeResult((tc) -> {
            String[] inOut = tc.split(SymbolConstant.SPACE);
            int[] numbers = ModelParser.parseIntArray(inOut[0]);
            Set<Integer> expect = ModelParser.parseSet(inOut[1], Integer.class);
            int actual = jz4.duplicate(numbers);
            OjAssertUtil.assertTrue(expect.contains(actual), actual + " is not in " + expect);
        }, "jz4", TargetType.JZ);
    }

    public int duplicate(int[] numbers) {
        // 合法的位置 i == numbers[i]
        for (int i = 0; i < numbers.length; i++) {
            while (i != numbers[i]) {
                if (numbers[i] == numbers[numbers[i]]) {
                    return numbers[i];
                }
                // 把 number[i] 交换到正确的位置上
                swap(numbers, i, numbers[i]);
            }
        }
        return -1;
    }

    void swap(int[] numbers, int i, int j) {
        int tmp = numbers[i];
        numbers[i] = numbers[j];
        numbers[j] = tmp;
    }
}
