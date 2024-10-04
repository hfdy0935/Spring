package com.hspedu.smallProj.calendar;

import org.junit.Test;

import java.time.LocalDate;

public class Calendar {
    public static void main(String[] args) {
        LocalDate now = LocalDate.now();
        int dayOfMonth = now.getDayOfMonth();
        int monthValue = now.getMonthValue();
        // 本月第一天星期几
        LocalDate firstDay = now.minusDays(dayOfMonth - 1);
        int firstDayWekDay = firstDay.getDayOfMonth();
        System.out.println("Mon Tue Wed Thu Fri Sat Sun");
        // 打印本月之前的日期
        for (int i = 0; i < firstDayWekDay; i++) {
            System.out.print("    ");
        }
        // 打印本月日期
        while (firstDay.getMonthValue() == monthValue) {
            int day = firstDay.getDayOfMonth();
            System.out.print(day < 10 ? " " + day : day);
            if (firstDay.getDayOfMonth() == now.getDayOfMonth()) {
                System.out.print("* ");
            } else {
                System.out.print("  ");
            }
            firstDay = firstDay.plusDays(1);
            if (firstDay.getDayOfWeek().getValue() == 7) {
                System.out.println();
            }
        }
    }

    @Test
    public void test(){
        System.out.println(LocalDate.now().getDayOfWeek().getValue());
    }
}
