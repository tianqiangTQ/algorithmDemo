package com.tq.sort;

import java.util.Arrays;

/**
 * 希尔排序
 */
public class ShellSort {

    public static void main(String[] args) {
        int[] arr = SortUtil.geneArr(8);
        System.out.println(Arrays.toString(arr));
//        sort(arr);
        sort2(arr);
        System.out.println("结果");
        System.out.println(Arrays.toString(arr));
    }

    /**
     * 交换法
     */
    private static void sort(int[] arr) {
        int tmp;
        // 步长从1/2开始，依次1/2
        for (int gap = arr.length / 2; gap > 0; gap = gap / 2) {
            // 每次循环确定开始节点
            for (int i = 0; i < arr.length; i++) {
                // 每次循环找到下一节点
                for (int j = i + gap; j < arr.length; j += gap) {
                    if (arr[j] < arr[i]) {
                        tmp = arr[j];
                        arr[j] = arr[i];
                        arr[i] = tmp;
                    }
                }
            }
        }
    }

    /**
     * 移动法
     */
    private static void sort2(int[] arr) {
        for (int gap = arr.length / 2; gap > 0; gap = gap / 2) {
            for (int i = gap; i < arr.length; i++) {
                int insertIndex = i;
                int insertValue = arr[i];
                while (insertIndex - gap >= 0 && insertValue < arr[insertIndex - gap]) {
                    // 右移
                    arr[insertIndex] = arr[insertIndex - gap];

                    insertIndex -= gap;
                }
                arr[insertIndex] = insertValue;
            }
        }

    }
}
