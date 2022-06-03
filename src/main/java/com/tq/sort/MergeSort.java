package com.tq.sort;

import java.util.Arrays;

/**
 * 归并排序
 */
public class MergeSort {
    public static void main(String[] args) {
        int[] arr = SortUtil.geneArr(8);
        System.out.println(Arrays.toString(arr));

        sort(arr);
        System.out.println("结果：");
        System.out.println(Arrays.toString(arr));
    }

    public static void sort(int[] arr) {
        int[] tmp = new int[arr.length];
        doSort(arr, 0, arr.length - 1, tmp);
    }

    public static void doSort(int[] arr, int left, int right, int[] tmp) {
        if (left >= right) {
            return;
        }
        int mid = (left + right) / 2;
        doSort(arr, left, mid, tmp);
        doSort(arr, mid + 1, right, tmp);
        doMerge(arr, left, mid, right, tmp);
    }

    public static void doMerge(int[] arr, int left, int mid, int right, int[] tmp) {
        int l = left;
        int r = mid + 1;
        int t = 0;
        // 循环处理，将小数放在tmp数组中
        while (l <= mid && r <= right) {
            if (arr[l] <= arr[r]) {
                tmp[t++] = arr[l];
                l++;
            } else {
                tmp[t++] = arr[r];
                r++;
            }
        }
        // 处理剩余的数
        while (l <= mid) {
            tmp[t++] = arr[l];
            l++;
        }
        while (r <= right) {
            tmp[t++] = arr[r];
            r++;
        }
        // 将tmp复制到arr
        for (int i = 0; i < t; i++) {
            arr[left++] = tmp[i];
        }
    }
}
