package com.tq.sort;

import java.util.Arrays;

/**
 * 冒泡排序
 */
public class BubbleSort {

    public static void main(String[] args) {
        int arr[] = new int[]{2, 4, 1, 5, 3};
        bubbleSort(arr);
        System.out.println(Arrays.toString(arr));
    }

    public static void bubbleSort(int arr[]) {
        int tmp;
        // 外循环：每次循环确定了第i个位置
        for (int i = 0; i < arr.length - 1; i++) {
            boolean swapped = false;

            // 内循环：每次循环比较当前位置和下一个位置，若发现逆序则交换
            for (int j = 0; j < arr.length - 1 - i; j++) {
                if (arr[j] > arr[j + 1]) {
                    swapped = true;
                    tmp = arr[j];
                    arr[j] = arr[j + 1];
                    arr[j + 1] = tmp;
                }
            }

            // 优化：没有交换过，代表已有序
            if (!swapped) {
                break;
            }
        }
    }

}
