package org.example.demo;

import java.util.Random;

public class QuickSortStrings {

    private static final Random random = new Random();

    public static void main(String[] args) {
        String[] strings = {"banana", "apple", "orange", "grape", "kiwi", "pear"};
        quickSort(strings, 0, strings.length - 1);
        System.out.println("Sorted array: " + java.util.Arrays.toString(strings));
    }

    public static void quickSort(String[] arr, int left, int right) {
        if (left < right) {
            int pi = partition(arr, left, right);
            quickSort(arr, left, pi - 1);
            quickSort(arr, pi + 1, right);
        }
    }

    public static int partition(String[] arr, int left, int right) {
        int pivotIdx = left + random.nextInt(right - left + 1);
        swap(arr, left, pivotIdx);

        String pivot = arr[left];
        int le = left + 1, ge = right;

        while (true) {
            while (le <= ge && arr[le].compareTo(pivot) < 0) {
                le++;
            }
            while (le <= ge && arr[ge].compareTo(pivot) > 0) {
                ge--;
            }

            if (le >= ge) {
                break;
            }

            swap(arr, le, ge);
            le++;
            ge--;
        }

        swap(arr, left, ge);
        return ge;
    }

    public static void swap(String[] arr, int i, int j) {
        String tmp = arr[i];
        arr[i] = arr[j];
        arr[j] = tmp;
    }
}
