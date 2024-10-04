package com.hspedu.chapter19;

import org.junit.Test;

import java.io.File;
import java.io.IOException;

public class FileTest {
    public static void main(String[] args) {
    }

    @Test
    public void test1() throws IOException {
        // 1.
        String path1 = "D:\\桌面\\t\\Java\\javaPractise\\src\\com\\hspedu\\assets\\test1.txt";
        File file1 = new File(path1);
        boolean res1 = file1.createNewFile();

        // 2.
        String dirname = "D:\\桌面\\t\\Java\\javaPractise\\src\\com\\hspedu\\assets";
        File file2 = new File(dirname);
        File file3 = new File(file2, "test2.txt");
        boolean res2 = file3.createNewFile();

        // 3.
        File file4 = new File(dirname, "test3.txt");
        boolean res3 = file4.createNewFile();

        boolean res = file1.renameTo(new File("D:\\桌面\\t\\Java\\javaPractise\\src\\com\\hspedu\\assets\\改名了.vue"));
        new File("D:\\桌面\\t\\Java\\javaPractise\\src\\com\\hspedu\\assets\\a\\b\\c").mkdirs();
    }
}
