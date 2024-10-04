package com.hspedu.chapter13.ArraysPractise;

import java.util.Calendar;

public class CalenderTest {
    public static void main(String[] args) {
        Calendar c = Calendar.getInstance();
        System.out.println("年：" + c.get(Calendar.YEAR)); // 年：2024
        System.out.println("月：" + c.get(Calendar.MONTH)); // 月：6
        System.out.println("日：" + c.get(Calendar.DAY_OF_MONTH)); // 日：10
        System.out.println("小时：" + c.get(Calendar.HOUR_OF_DAY)); // 小时：22
        System.out.println("分钟：" + c.get(Calendar.MINUTE)); // 分钟：2
        System.out.println("秒：" + c.get(Calendar.SECOND)); // 秒：38
    }
}
