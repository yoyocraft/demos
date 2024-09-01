package org.example.algo.test;


/**
 * @author yoyocraft
 * @date 2024/08/24
 */
public class SearchNearest {

    public static void main(String[] args) {
        int[] arr = {1, 3, 5, 6, 10};
        int target = 3;
        System.out.println(searchNearest(arr, target));
    }

    public static int searchNearest(int[] arr, int target) {
        int left = 0;
        int right = arr.length - 1;
        // 此时 left 和 right 不相邻，继续比较
        while (right - left > 1) {
            int mid = left + (right - left) / 2;
            if (target < arr[mid]) {
                right = mid;
            } else {
                left = mid;
            }
        }

        return Math.abs(arr[left] - target) <= Math.abs(arr[right] - target) ? arr[left] : arr[right];
    }
}
