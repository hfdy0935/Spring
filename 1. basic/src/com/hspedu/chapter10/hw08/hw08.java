package com.hspedu.chapter10.hw08;

public class hw08 {
    public static void main(String[] args) {
        Color green = Color.GREEN;
        switch (green) {
            case RED:
                System.out.println("red");
                break;
            case BLUE:
                System.out.println("blue");
                break;
            case YELLOW:
                System.out.println("yellow");
                break;
            case GREEN:
                System.out.println("green");
                break;
            case BLACK:
                System.out.println("break");
                break;
            default:
                System.out.println("没匹配到");
        }
    }
}

interface Show {
    void show();
}

enum Color implements Show {
    RED(255, 0, 0),
    BLUE(0, 0, 255),
    BLACK(0, 0, 0),
    YELLOW(255, 255, 0),
    GREEN(0, 255, 0);

    private int redValue;
    private int greenValue;
    private int blueValue;

    private Color(int redValue, int greenValue, int blueValue) {
        this.redValue = redValue;
        this.greenValue = greenValue;
        this.blueValue = blueValue;
    }

    @Override
    public void show() {
        System.out.println("red: " + redValue + ", green: " + greenValue + ", blue: " + blueValue);
    }

}