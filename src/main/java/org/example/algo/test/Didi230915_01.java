package org.example.algo.test;

import java.util.Scanner;

/**
 * @author yoyocraft
 * @date 2024/09/05
 */
public class Didi230915_01 {

    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        int n = in.nextInt();
        int k = in.nextInt();
        int[] arr = new int[n];
        for (int i = 0; i < n; i++) {
            arr[i] = in.nextInt();
        }

        int l = 0, r = 1000000000, ans = -1;
        while (l <= r) {
            int mid = l + (r - l) / 2;
            if (check(arr, k, mid)) {
                ans = mid;
                l = mid + 1;
            } else {
                r = mid - 1;
            }
        }
        System.out.println(ans);
        in.close();
    }

    private static boolean check(int[] arr, int k, int dis) {
        int cnt = 1;
        int last = arr[0];
        for (int i = 1; i < arr.length && cnt < k; i++) {
            if (arr[i] >= last + dis) {
                cnt++;
                last = arr[i];
            }
        }
        return cnt >= k;
    }
}
