package com.hspedu.chapter08.hw09;

public class LabelPoint extends Point {
    private final String label;

    public LabelPoint(String label, int x, int y) {
        super(x, y);
        this.label = label;
    }

    public String getLabel() {
        return this.label;
    }

    public boolean isSame(LabelPoint p) {
        return super.isSame(p);
    }
}
