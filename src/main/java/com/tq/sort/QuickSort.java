package com.tq.sort;

import java.util.Arrays;

/**
 * 快速排序
 */
public class QuickSort {

    public static void main(String[] args) {
        int[] arr = SortUtil.geneArr(10);
        System.out.println(Arrays.toString(arr));
        sort(arr);
        System.out.println("结果：");
        System.out.println(Arrays.toString(arr));

    }

    public static void sort(int[] arr) {
        int left = 0;
        int right = arr.length - 1;
        doSort(arr, left, right);
    }

    private static void doSort(int[] arr, int left, int right) {
        int mid = (left + right) / 2;
        int pivot = arr[mid];
        int tmp;
        int l = left;
        int r = right;
        while (l < r) {
            while (arr[l] < pivot) {
                l++;
            }
            while (arr[r] > pivot) {
                r--;
            }
            if (l >= r) {
                break;
            }
            // 上面找到了需要交换的两个数的位置，进行交换
            tmp = arr[l];
            arr[l] = arr[r];
            arr[r] = tmp;

            // 交换值 有可能和pivot值相等，进行特殊处理
            if (pivot == arr[l]) {
                r--;
            }
            if (pivot == arr[right]) {
                l++;
            }

        }

        if (l == r) {
            l++;
            r--;
        }

        if (left < r) {
            doSort(arr, left, r);
        }
        if (right > l) {
            doSort(arr, l, right);
        }
    }
}
