package com.tq.search;

import java.util.Arrays;

public class BinarySearch {
    public static void main(String[] args) {
        int[] arr = {3, 6, 14, 18, 23, 34};
        System.out.println(Arrays.toString(arr));

        int i = find(arr, 23);
        System.out.println(i);
    }

    public static int find(int[] arr, int key) {
        return doFind(arr, 0, arr.length - 1, key);
    }

    private static int doFind(int[] arr, int left, int right, int key) {
        if (left > right) {
            return -1;
        }
        int mid = (left + right) / 2;
        int midVal = arr[mid];
        if (midVal < key) {
            // 向右
            return doFind(arr, mid + 1, right, key);
        } else if (midVal > key) {
            // 向左
            return doFind(arr, left, mid - 1, key);
        } else {
            return mid;
        }
    }
}
