package com.hspedu.chapter08.hw09;

public class Point {
    protected double x;
    protected double y;

    public Point(double x, double y) {
        this.x = x;
        this.y = y;
    }

    public boolean isSame(Point p) {
        return this.x == p.x && this.y == p.y;
    }
}
