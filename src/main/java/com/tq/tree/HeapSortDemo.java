package com.tq.tree;

import java.util.Arrays;

public class HeapSortDemo {
    public static void main(String[] args) {
        int[] arr = {4, 6, 8, 5, 9};

        sort(arr);
        System.out.println(Arrays.toString(arr));
    }

    public static void sort(int[] arr) {
        // 从小到大用大顶堆
        // 构建一个大顶堆
        // 从左下第一个非叶子节点开始
        for (int i = arr.length / 2 - 1; i >= 0; i--) {
            heepAdjust(arr, i, arr.length);
        }

        // 交换第一个元素和最后一个元素
        int tmp;
        for (int i = arr.length - 1; i > 0; i--) {
            tmp = arr[0];
            arr[0] = arr[i];
            arr[i] = tmp;
            heepAdjust(arr, 0, i);
        }

    }

    public static void heepAdjust(int[] arr, int i, int length) {
        int tmp = arr[i];
        // 从左子树开始遍历
        for (int k = leftChild(i); k < length; k = leftChild(k)) {
            // 如果右子树大，则指向右子树
            if (k + 1 < length && arr[k] < arr[k + 1]) {
                k++;
            }
            // 若子节点大，则使用子节点的值覆盖该节点，并将子节点的位置交给i
            if (tmp < arr[k]) {
                arr[i] = arr[k];
                i = k;
            } else {
                break;
            }

        }
        arr[i] = tmp;
    }

    public static int leftChild(int i) {
        return 2 * i + 1;
    }

    public static int rightChild(int i) {
        return 2 * i + 2;
    }
}
