package com.tq.recursion;

import java.util.Arrays;

/**
 * 八皇后
 */
public class EightQueue {
    static int max = 8;
    // 最终结果。下标代表第几个皇后，也代表第几行，value代表该皇后放在第几列
    int[] arr = new int[8];

    static int count = 0;

    public static void main(String[] args) {
        EightQueue eightQueue = new EightQueue();
        eightQueue.place(0);
        System.out.println(count);
    }

    /**
     * 放置第index个皇后
     */
    public void place(int index) {
        if (index >= max) {
            count++;
            System.out.println(Arrays.toString(arr));
//            arr = new int[8];
            return;
        }
        // col代表放在第几列
        for (int col = 0; col < arr.length; col++) {
            arr[index] = col;
            if (canPlace(index)) {
                // 放置下一个皇后
                place(index + 1);
            }
        }
    }

    /**
     * 能否放置第index个皇后
     */
    public boolean canPlace(int index) {
        for (int i = 0; i < index; i++) {
            // 不能在同一列或斜线上(天然不在同一行，因为index具有双重身份：第几个皇后+第几行)
            if (arr[i] == arr[index] || Math.abs(i - index) == Math.abs(arr[i] - arr[index])) {
                return false;
            }
        }
        return true;
    }

}
