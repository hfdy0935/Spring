package com.hspedu.chapter11;

public class Enumeration2 {
    public static void main(String[] args) {
        System.out.println(Season2.SPRING);
        System.out.println(Season2.SUMMER);
        System.out.println(Season2.AUTUMN);
        System.out.println(Season2.WINTER);
        // 常用方法
        System.out.println(Season2.SPRING.name());
        System.out.println(Season2.SPRING.ordinal());
        Season2[] values = Season2.values();
        for (Season2 season : values) {
            System.out.println(season);
        }
        System.out.println(Season2.valueOf("SPRING"));
        System.out.println(Season2.SPRING.compareTo(Season2.SUMMER));
    }
}

enum Season2 {
    SPRING("春天", "温暖"),
    SUMMER("夏天", "炎热"),
    AUTUMN("秋天", "凉爽"),
    WINTER("冬天", "寒冷");


    private String name;
    private String desc;

    private Season2(String name, String desc) {
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
        return "Season2{" +
                "name='" + name + '\'' +
                ", desc='" + desc + '\'' +
                '}';
    }
}