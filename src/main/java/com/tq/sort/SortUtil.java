package com.tq.sort;

import java.util.Random;

public class SortUtil {

    public static int[] geneArr(int length) {
        int[] arr = new int[length];
        for (int i = 0; i < length; i++) {
            arr[i] = new Random().nextInt(length * 13);
        }
        return arr;
    }
}
