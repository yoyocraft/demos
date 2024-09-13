package org.example.algo.test;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

/**
 * @author yoyocraft
 * @date 2024/09/11
 */
public class Main {
    static final Map<Integer, String> sort1Names = new HashMap<>();
    static final Map<Integer, String> sort2Names = new HashMap<>();

    static {
        sort1Names.put(8, "ace");
        sort1Names.put(2, "bre");
        sort1Names.put(5, "cat");
        sort1Names.put(1, "dog");

        sort2Names.put(4, "ace");
        sort2Names.put(3, "bre");
        sort2Names.put(2, "cat");
        sort2Names.put(1, "dog");
    }


    public static void main(String[] args) {
        int[] sort1 = {8, 5, 2, 1};
        int[] sort2 = {4, 3, 2, 1};

        Scanner in = new Scanner(System.in);
        String line = in.nextLine();
        String[] inOut = line.split(" ");
        String orderBy = inOut[0];
        String orderType = inOut[1];

        int[] idx = new int[0];
        Map<Integer, String> nameMapping = new HashMap<>();
        if ("sort1".equals(orderBy)) {
            idx = sort1;
            nameMapping = sort1Names;
        } else if ("sort2".equals(orderBy)) {
            idx = sort2;
            nameMapping = sort2Names;
        }

        if ("asc".equals(orderType)) {
            Arrays.sort(idx);
        }

        for (int i : idx) {
            System.out.println(nameMapping.get(i));
        }
    }

}
