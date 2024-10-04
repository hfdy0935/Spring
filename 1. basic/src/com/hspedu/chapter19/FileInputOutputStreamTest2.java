package com.hspedu.chapter19;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;

public class FileInputOutputStreamTest2 {
    public static void main(String[] args) {
        String basePath = "D:\\桌面\\t\\Java\\javaPractise\\src\\com\\hspedu\\assets";
        // 拷贝图片
        try {
            FileInputStream fileInputStream = new FileInputStream(new File(basePath, "bomb_1.gif"));
            FileOutputStream fileOutputStream = new FileOutputStream(new File(basePath, "bomb_1_copy.gif"));
            int data;
            // 每次读取1024字节，1K
            while ((data = fileInputStream.read(new byte[1024])) != -1) {
                fileOutputStream.write(data);
            }
            fileInputStream.close();
            fileOutputStream.close();
        } catch (IOException e) {
            System.out.println("出错了 " + e.getMessage());
        }
    }
}
