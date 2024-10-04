package com.hspedu.chapter13.hw04;

public class hw04 {
    public static void main(String[] args) {
        String str = "asdjieo12091384ruSJKDFHBEVsidjfhJHBSDsjdn10293i0";
        count(str);
    }

    public static void count(String str) {
        if (str == null) {
            System.out.println("不能为空");
            return;
        }
        int numCount = 0;
        int lowerCount = 0;
        int upperCount = 0;
        for (char c : str.toCharArray()) {
            if (c >= '0' && c <= '9') {
                numCount++;
            } else if (c >= 'a' && c <= 'z') {
                lowerCount++;
            } else if (c >= 'A' && c <= 'Z') {
                upperCount++;
            }
        }
        System.out.println("数字：" + numCount + "个\n" + "小写字母：" + upperCount + "个\n" + "大写字母：" + lowerCount + "个");
    }
}
