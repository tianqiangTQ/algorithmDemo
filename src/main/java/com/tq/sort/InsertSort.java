package com.tq.sort;

import java.util.Arrays;

/**
 * 插入排序
 */
public class InsertSort {
    public static void main(String[] args) {
        int[] arr = SortUtil.geneArr(8);
        System.out.println(Arrays.toString(arr));
        System.out.println("结果");
        sort(arr);
        System.out.println(Arrays.toString(arr));

    }

    public static void sort(int[] arr) {
        // 把数组分成两类：有序数组、无序数组
        // 先从第0个位置开始，分为[0][1,2,..]两组数组
        // 每次循环确定第i个数字的插入
        for (int i = 1; i < arr.length; i++) {
            int insertIndex = i;
            int insertValue = arr[i];

            // 每次循环找到第j个数字的待插入位置
            while (insertIndex > 0 && insertValue < arr[insertIndex - 1]) {
                // 当前位置 置换成 前一个位置的值，即前一个位置后移
                arr[insertIndex] = arr[insertIndex - 1];
                // 寻找下一个位置：从右向左
                insertIndex--;
            }
            // 执行插入
            arr[insertIndex] = insertValue;
        }
    }
}
