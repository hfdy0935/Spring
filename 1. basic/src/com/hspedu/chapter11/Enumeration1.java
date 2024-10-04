package com.hspedu.chapter11;

public class Enumeration1 {
    public static void main(String[] args) {
        System.out.println(Season1.SPRING);
        System.out.println(Season1.SUMMER);
        System.out.println(Season1.AUTUMN);
        System.out.println(Season1.WINTER);
    }
}


class Season1 {
    private String name;
    private String desc;
    public static final Season1 SPRING = new Season1("春天", "温暖");
    public static final Season1 SUMMER = new Season1("夏天", "炎热");
    public static final Season1 AUTUMN = new Season1("秋天", "凉爽");
    public static final Season1 WINTER = new Season1("冬天", "寒冷");

    private Season1(String name, String desc) {
        this.name = name;
        this.desc = desc;
    }

    public String getName() {
        return name;
    }

    public String getDesc() {
        return desc;
    }

    @Override
    public String toString() {
        return "Season1{" +
                "name='" + name + '\'' +
                ", desc='" + desc + '\'' +
                '}';
    }
}