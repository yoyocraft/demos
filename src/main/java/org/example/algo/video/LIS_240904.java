package org.example.algo.video;

import org.example.algo.ModelParser;
import org.example.algo.OjAssertUtil;
import org.example.algo.TargetType;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * @author yoyocraft
 * @date 2024/09/04
 */
public class LIS_240904 {

    public static void main(String[] args) {
        LIS_240904 solution = new LIS_240904();
        OjAssertUtil.judgeResult((tc) -> {
            int[] nums = ModelParser.parseIntArray(tc);
            int[] lis = solution.getLIS(nums);
            System.out.printf("in: %s, out: %s%n", tc, ModelParser.parseString(lis));
        }, "lis_240904", TargetType.VIDEO);
    }

    public int[] getLIS(int[] nums) {
        if (nums == null || nums.length <= 1) {
            return nums;
        }

        int n = nums.length;
        List<List<Integer>> lisList = new ArrayList<>();
        lisList.add(Collections.singletonList(nums[0]));

        for (int i = 1; i < n; i++) {
            appendLIS(lisList, nums[i]);
        }
        return lisList.get(lisList.size() - 1).stream().mapToInt(Integer::intValue).toArray();
    }

    private void appendLIS(List<List<Integer>> lisList, int x) {
        for(int i = lisList.size() - 1; i >= 0; i--) {
            List<Integer> line = lisList.get(i);
            int tail = line.get(line.size() - 1);
            if(x > tail) {
                List<Integer> newLine = new ArrayList<>(line);
                newLine.add(x);
                // 更新 i+1 行
                if(lisList.size() > i + 1) {
                    lisList.set(i + 1, newLine);
                } else {
                    lisList.add(newLine);
                }
                return ;
            }
        }
        // i == -1
        lisList.set(0, Collections.singletonList(x));
    }
}
