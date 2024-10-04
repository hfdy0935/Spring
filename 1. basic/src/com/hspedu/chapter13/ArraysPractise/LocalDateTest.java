package com.hspedu.chapter13.ArraysPractise;

import java.time.Instant;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.Date;

public class LocalDateTest {
    public static void main(String[] args) {
        LocalDateTime ldt = LocalDateTime.now();
        System.out.println(ldt); // 2024-07-10T22:07:36.767
        System.out.println(ldt.getYear() + " " + ldt.getMonthValue() + " " + ldt.getDayOfMonth()); // 2024 7 10
        LocalDate ld = LocalDate.now();
        System.out.println(ld); // 2024-07-10
        System.out.println(ld.getYear() + " " + ld.getMonthValue() + " " + ld.getDayOfMonth()); // 2024 7 10
        LocalTime lt = LocalTime.now();
        System.out.println(lt); // 22:08:29.989
        System.out.println(lt.getHour() + " " + lt.getMinute() + " " + lt.getSecond()); // 22 10 24

        // 日期加减
        LocalDateTime ldt2 = ldt.plusDays(100);
        System.out.println(ldt2); // 2024-10-18T22:12:53.726
        LocalDateTime ldt3 = ldt.minusMinutes(100);
        System.out.println(ldt3); // 2024-07-10T20:32:53.726

        // 格式化
        DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
        System.out.println(dtf.format(ldt)); // 2024-07-10 22:14:53


        // 时间戳
        Instant now = Instant.now();
        System.out.println(now);
        Date date = Date.from(now);
        Instant instant2 = date.toInstant();
    }
}
