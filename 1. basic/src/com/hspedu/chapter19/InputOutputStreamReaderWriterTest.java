package com.hspedu.chapter19;

import java.io.*;

public class InputOutputStreamReaderWriterTest {
    public static void main(String[] args) throws IOException {
        String inPath = "D:\\桌面\\t\\Java\\javaPractise\\src\\com\\hspedu\\assets\\fileRWTest.tsx";
        // 把输入流二进制数据转为字符串
        // 第二个参数默认utf-8
        InputStreamReader isr = new InputStreamReader(new FileInputStream(inPath), "utf-8");
        BufferedReader br = new BufferedReader(isr);
        int data;
        StringBuffer sb = new StringBuffer();
        while ((data = br.read()) != -1) {
            sb.append((char) data);
        }
        System.out.println(sb.toString());
        br.close();

        // 输出
        String outPath = "D:\\桌面\\t\\Java\\javaPractise\\src\\com\\hspedu\\assets\\outputStreamWriter.tsx";
        OutputStreamWriter osw = new OutputStreamWriter(new FileOutputStream(outPath), "utf-8");
        osw.write(sb.toString());
        osw.close();
    }
}
