package org.example.algo;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Random;

public class RedEnvelopeDistributor {
    public static List<Integer> distribute(int totalAmount, int n, int minAmount, int maxAmount) {
        if (minAmount * n > totalAmount || maxAmount * n < totalAmount) {
            throw new IllegalArgumentException("无法满足条件的分配方案");
        }

        List<Integer> result = new ArrayList<>();
        Random random = new Random();

        // 初步分配，确保每个人至少得到minAmount
        for (int i = 0; i < n; i++) {
            result.add(minAmount);
        }

        // 剩余的金额（以分为单位）
        int remainingAmount = totalAmount - minAmount * n;

        // 开始随机分配剩余的金额
        for (int i = 0; i < n - 1; i++) {
            // 剩余最大可分配金额（确保剩下的人可以得到至少minAmount）
            int maxAvailable = Math.min(remainingAmount, maxAmount - minAmount);
            int randomAmount = random.nextInt(maxAvailable + 1);
            result.set(i, result.get(i) + randomAmount);
            remainingAmount -= randomAmount;
        }

        // 将剩余金额分配给最后一个人
        result.set(n - 1, result.get(n - 1) + remainingAmount);

        // 打乱顺序以增加随机性
        Collections.shuffle(result);

        return result;
    }

    public static void main(String[] args) {
        int totalAmount = 10000; // 总金额，单位为分（100元）
        int n = 5;
        int minAmount = 1000; // 最小金额，单位为分（10元）
        int maxAmount = 3000; // 最大金额，单位为分（30元）

        List<Integer> result = distribute(totalAmount, n, minAmount, maxAmount);
        System.out.println(result);
    }
}
