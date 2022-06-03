package com.tq.recursion;

/**
 * 简单迷宫
 */
public class MiGong {
    static int ROW_MAX = 8;
    static int COL_MAX = 7;
    static int WALL = 1;
    static int PASS = 2;
    static int NOT_PASS = 3;

    public static void main(String[] args) {
        int[][] map = new int[ROW_MAX][COL_MAX];
        for (int i = 0; i < map[0].length; i++) {
            map[0][i] = WALL;
            map[ROW_MAX - 1][i] = WALL;
        }
        for (int i = 0; i < map.length; i++) {
            map[i][0] = WALL;
            map[i][COL_MAX - 1] = WALL;
        }
        map[3][1] = WALL;
        map[3][2] = WALL;
        // 原始迷宫
        print(map);

        // 走迷宫
//        process(map, 1, 1);
        process2(map, 1, 1);
        System.out.println("结果：");
        print(map);
    }

    private static boolean process2(int[][] map, int row, int col) {
        // 终点
        if (map[6][5] == PASS) {
            return true;
        }

        if (map[row][col] == 0) {
            map[row][col] = PASS;
            // 右 下 左 上
            if (process2(map, row, col + 1)) {
                return true;
            } else if (process2(map, row + 1, col)) {
                return true;
            } else if (process2(map, row, col - 1)) {
                return true;
            } else if (process2(map, row - 1, col)) {
                return true;
            } else {
                map[row][col] = NOT_PASS;
                return false;
            }
        }
        return false;

    }

    private static void process(int[][] map, int row, int col) {
        // 终点
        if (map[6][5] == PASS) {
            return;
        }

        map[row][col] = PASS;
        // 右 下 左 上
        if (judge(map, row, col + 1)) {
            process(map, row, col + 1);
        } else if (judge(map, row + 1, col)) {
            process(map, row + 1, col);
        } else if (judge(map, row, col - 1)) {
            process(map, row, col - 1);
        } else if (judge(map, row - 1, col)) {
            process(map, row - 1, col);
        } else {
            map[row][col] = NOT_PASS;
        }

    }

    private static boolean judge(int[][] map, int row, int col) {
        if (row < 0 || row > ROW_MAX - 1 || col < 0 || col > COL_MAX - 1) {
            return false;
        }
        return map[row][col] == 0;
    }

    private static void print(int[][] map) {
        for (int row = 0; row < map.length; row++) {
            for (int col = 0; col < map[0].length; col++) {
                System.out.printf("%s ", map[row][col]);
            }
            System.out.println();
        }
    }
}
