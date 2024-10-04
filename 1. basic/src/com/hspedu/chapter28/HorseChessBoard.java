package com.hspedu.chapter28;

import java.awt.*;
import java.util.ArrayList;

public class HorseChessBoard {
    private static final int X = 6;
    private static final int Y = 6;
    private static final int[][] chessBoard = new int[Y][X]; // 棋盘
    private static final boolean[] visited = new boolean[X * Y]; // 记录某个位置是否走过
    private static boolean finished = false; // 是否遍历完

    public static void main(String[] args) {
        int row = 3;
        int col = 3;
        long start = System.currentTimeMillis();
        traversalChessBoard(row - 1, col - 1, 1);
        System.out.println("共耗时：" + (System.currentTimeMillis() - start) + "ms");
        for (int[] ints : chessBoard) {
            for (int i : ints) {
                System.out.printf("%3d",i);
            }
            System.out.println();
        }
    }

    /**
     * core，遍历棋盘，如果遍历成功，则把finish设置为true
     *
     * @param row  这次遍历的起点y
     * @param col  这次遍历的起点x
     * @param step 这次遍历是第几步
     */
    public static void traversalChessBoard(int row, int col, int step) {
        chessBoard[row][col] = step;
        // 把该位置设置为已访问
        visited[row * X + col] = true;
        // 获取当前该位置可以走的下一个位置
        ArrayList<Point> ps = next(new Point(col, row));
        while (!ps.isEmpty()) {
            // 取出一个点
            Point p = ps.remove(0);
            // 的判断该位置是否走过，如果未走过则递归遍历
            if (!visited[p.y * X + p.x]) {
                traversalChessBoard(p.y, p.x, step + 1);
            }
        }
        // 退出while循环时，判断是否遍历成功
        // 若不成功则重置，回溯
        if (step < X * Y && !finished) {
            chessBoard[row][col] = 0;
            visited[row * X + col] = false;
        } else {
            finished = true;
        }
    }

    // 获取当前位置可以走的下一步所有位置
    public static ArrayList<Point> next(Point currPoint) {
        ArrayList<Point> points = new ArrayList<>();
        // 判断在当前位置是否可以走到下一个位置，如果能就加到points
        int[] posArr = new int[]{-2, -1, 1, 2};
        for (int x : posArr) {
            for (int y : posArr) {
                if (Math.abs(x) == Math.abs(y)) continue;
                int targetX = currPoint.x + x;
                int targetY = currPoint.y + y;
                if (targetX >= 0 && targetX < X && targetY >= 0 && targetY < Y) {
                    points.add(new Point(targetX, targetY));
                }
            }
        }
        return points;
    }
}
