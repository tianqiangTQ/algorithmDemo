package com.tq.sort;

import java.util.Arrays;

/**
 * 基数排序
 */
public class RadixSort {

    public static void main(String[] args) {
        int[] arr = SortUtil.geneArr(8);

        System.out.println(Arrays.toString(arr));

        sort(arr);
        System.out.println("结果：");
        System.out.println(Arrays.toString(arr));

    }

    public static void sort(int[] arr) {
        int[][] bucket = new int[10][arr.length];
        int[] bucketItemCount = new int[10];

        int max = arr[0];
        for (int value : arr) {
            if (max < value) {
                max = value;
            }
        }
        int length = String.valueOf(max).length();
        for (int p = 1; String.valueOf(p).length() <= length; p *= 10) {
            for (int i = 0; i < arr.length; i++) {
                int bucketIndex = arr[i] / p % 10;
                int count = bucketItemCount[bucketIndex]++;
                bucket[bucketIndex][count] = arr[i];
            }
            // 从bucket中复制到arr
            int index = 0;
            for (int i = 0; i < 10; i++) {
                for (int j = 0; j < bucketItemCount[i]; j++) {
                    arr[index++] = bucket[i][j];
                }
                // 回归指针
                bucketItemCount[i] = 0;
            }
        }
    }
}
