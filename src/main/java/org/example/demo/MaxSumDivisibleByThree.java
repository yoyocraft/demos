package org.example.demo;

import java.util.Arrays;

public class MaxSumDivisibleByThree {

    public static int maxSumDivThree(int[] nums) {
        int sum = Arrays.stream(nums).sum();  // 计算总和
        int remainder = sum % 3;

        if (remainder == 0) {
            return sum;  // 如果总和已经能被3整除，直接返回
        }

        // 初始化最小值，设置为足够大
        int min1 = Integer.MAX_VALUE;
        int min2_1 = Integer.MAX_VALUE;
        int min2_2 = Integer.MAX_VALUE;

        // 遍历数组，找出最小的需要调整的值
        for (int num : nums) {
            int mod = num % 3;
            if (mod == 1) {
                min1 = Math.min(min1, num);  // 最小的余1的数
            } else if (mod == 2) {
                if (num < min2_1) {
                    min2_2 = min2_1;
                    min2_1 = num;  // 更新最小和次小的余2的数
                } else if (num < min2_2) {
                    min2_2 = num;
                }
            }
        }

        // 根据余数情况调整 sum
        int option1;
        int option2;
        if (remainder == 1) {
            option1 = min1;
            option2 = min2_1 + min2_2;
        } else {
            option1 = min2_1;
            option2 = min1 + min2_2;
        }
        return sum - Math.min(option1, option2);
    }

    public static void main(String[] args) {
        int[] nums = {3, 6, 5, 1, 8};
        System.out.println(maxSumDivThree(nums));  // 输出 18
    }
}
