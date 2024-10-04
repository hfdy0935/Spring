package com.hspedu.chapter13.ArraysPractise;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class DateTest {
    public static void main(String[] args) throws ParseException {
        Date d1 = new Date();
        System.out.println(d1); // Wed Jul 10 21:52:31 CST 2024
        Date d2 = new Date(923728739); // 指定毫秒数
        System.out.println(d2); // Mon Jan 12 00:35:28 CST 1970

        // 格式化
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        String str = sdf.format(d1);
        System.out.println(str); // 2024-07-10 21:54:23
        System.out.println(sdf.parse(str)); // 字符串解析会日期，小心会ParseException Wed Jul 10 21:56:40 CST 2024
    }
}
