package org.example.algo.test;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;

/**
 * 某实验室里有 boxs 盒液体试剂，其中一盒含有有毒物质，能够让小白鼠变黑，其余试剂为水。现在要找到哪盒试剂内含有有毒物质，可以投喂给一些小白鼠服用，通过观察小白鼠是否变黑进行判断。
 * <p>
 * 假设当前仅有 minutesToCheck 分钟来完成上述任务，并设定投喂规则如下：
 * 1. 允许小白鼠同时饮用任意数量的试剂内的液体，并且此过程不需要消耗时间。
 * 2. 小白鼠饮用完液休后，必须有minutesToWait分钟的等待时间，在这段时间里只能观察，不允许继续喂小白鼠。
 * 3. 过了 minutesToWait 分钟后，喝到有毒物质的小白鼠会变黑，其他的小白鼠毛色不变。
 * 4. 重复上述过程，直到时间用完。
 * <p>
 * 现给正试剂盒的数目boxs,minutesToWait和minutesToCheck，请编写代码返回完成目标任务（在规定时间内判断哪盒试剂内有有毒物质）所需要的最少小白鼠的数目。
 * <p>
 *
 * 输入描述：
 * 第一行输入三个整数，分别表示 boxs， minutesToWait 和 minutesToCheck
 * <p>
 * 输出描述：
 * 一个整数，表示规定时间内判断哪盒试剂内有有毒物质所需的最小小白鼠的数目。
 * <p>
 * 输入示例：
 * 500 20 40
 * <p>
 * 输出示例：
 * 6
 */
public class Test0922_03 {

    public static void main(String[] args) throws IOException {
        BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
        PrintWriter out = new PrintWriter(new OutputStreamWriter(System.out));
        String[] inOut = in.readLine().split(" ");
        int boxs = Integer.parseInt(inOut[0]);
        int minutesToWait = Integer.parseInt(inOut[1]);
        int minutesToCheck = Integer.parseInt(inOut[2]);

        int rounds = minutesToCheck / minutesToWait;

        int base = rounds + 1;

        int left = 0, right = boxs;
        while (left < right) {
            int mid = (left + right) / 2;
            if (fastPow(base, mid) >= boxs) {
                right = mid;
            } else {
                left = mid + 1;
            }
        }

        out.println(left);
        out.flush();
        out.close();
        in.close();
    }

    private static long fastPow(int x, int n) {
        long result = 1;
        long base = x;
        while (n > 0) {
            if ((n & 1) == 1) {
                result *= base;
            }
            base *= base;
            n >>= 1;
        }
        return result;
    }

}


// import java.io.BufferedReader;
// import java.io.IOException;
// import java.io.InputStreamReader;
// import java.io.OutputStreamWriter;
// import java.io.PrintWriter;
//
// public class Main {
//     public static void main(String[] args) throws IOException {
//         BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
//         PrintWriter out = new PrintWriter(new OutputStreamWriter(System.out));
//         String word1 = in.readLine();
//         String word2 = in.readLine();
//         out.println(minDistance(word1, word2));
//         out.flush();
//         out.close();
//         in.close();
//     }
//
//     static int minDistance(String word1, String word2) {
//         int m = word1.length(), n = word2.length();
//         if (m == 0) {
//             return n;
//         }
//         if (n == 0) {
//             return m;
//         }
//
//         char[] s = word1.toCharArray(), t = word2.toCharArray();
//         // f[i+1][j+1]: 表示 s[0...i] 转换成 t[0...j] 的最小次数
//         int[][] f = new int[m + 1][n + 1];
//         for (int j = 1; j <= n; j++) {
//             f[0][j] = j;
//         }
//         for (int i = 1; i <= m; i++) {
//             f[i][0] = i;
//         }
//
//         for (int i = 0; i < m; i++) {
//             for (int j = 0; j < n; j++) {
//                 if (s[i] == t[j]) {
//                     f[i + 1][j + 1] = f[i][j];
//                 } else {
//                     f[i + 1][j + 1] = Math.min(
//                             f[i + 1][j],
//                             Math.min(f[i][j + 1], f[i][j])
//                     ) + 1;
//                 }
//             }
//         }
//         return f[m][n];
//     }
// }
