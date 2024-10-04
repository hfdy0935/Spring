package com.hspedu.chapter08.hw09;

public class hw09 {
    public static void main(String[] args) {
        Point p1 = new Point(1, 2);
        Point p2 = new Point(1, 2);
        System.out.println(p1.isSame(p2)); // true
        LabelPoint p3 = new LabelPoint("a", 3, 4);
        LabelPoint p4 = new LabelPoint("b", 3, 4);
        System.out.println(p3.isSame(p4)); // true
    }
}
