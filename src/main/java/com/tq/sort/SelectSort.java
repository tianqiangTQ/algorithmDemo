package com.tq.sort;

import java.util.Arrays;

/**
 * 选择排序
 */
public class SelectSort {
    public static void main(String[] args) {
        int[] arr = SortUtil.geneArr(8);

        System.out.println(Arrays.toString(arr));
        sort(arr);

        System.out.println("结果：");
        System.out.println(Arrays.toString(arr));
    }


    public static void sort(int[] arr) {

        // 确定第i个位置，方式：把最小值位置与当前位置交换
        for (int i = 0; i < arr.length - 1; i++) {
            int minIndex = i;
            // 内循环：寻找最小值的位置
            for (int j = i + 1; j < arr.length; j++) {
                if (arr[j] < arr[minIndex]) {
                    minIndex = j;
                }
            }
            if (minIndex != i) {
                int tmp = arr[i];
                arr[i] = arr[minIndex];
                arr[minIndex] = tmp;
            }
        }
    }
}
