package com.hspedu.chapter19;

import java.io.*;

public class PrintStreamWriterTest {
    public static void main(String[] args) throws IOException {
        String path = "D:\\桌面\\t\\Java\\javaPractise\\src\\com\\hspedu\\assets\\Print.txt";
        // PrintWriter
        PrintWriter pw = new PrintWriter(new FileWriter(path));
        pw.println("123456");
        pw.close();
        // PrintStream
        PrintStream out = System.out;
        out.println("111111"); // 命令行输出
        out.close();
        PrintStream out1 = new PrintStream(new FileOutputStream(path, true)); // 追加
        out1.println("222222");
        out1.close();

    }
}
