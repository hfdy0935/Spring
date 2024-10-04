package com.hspedu.chapter19;

import org.junit.Test;

import java.io.*;

public class FileInputOutputStreamTest1 {
    public static void main(String[] args) {
    }

    @Test
    public void readFile1() {
        // 1. 创建文件
        String path1 = "D:\\桌面\\t\\Java\\javaPractise\\src\\com\\hspedu\\assets\\fileIOTest.tsx";
        File file1 = new File(path1);
        if (!file1.exists()) {
            try {
                file1.createNewFile();
                System.out.println("创建成功");
            } catch (IOException e) {
                System.out.println("创建失败");
                System.out.println(e.getMessage());
                return;
            }
        }
        // 2. 写入
        String templateCode = "import {useState} from 'react';\n" +
                "\n" +
                "const App = () => {\n" +
                "    const [num, setNum] = useState<number>(0);\n" +
                "    const onClick = () => {\n" +
                "        setNum(i => i + 1);\n" +
                "    }\n" +
                "\n" +
                "    return (\n" +
                "        <div>\n" +
                "            <div>{num}</div>\n" +
                "            <button onClick={onClick}>num + 1</button>\n" +
                "        </div>\n" +
                "    )\n" +
                "}";
        try {
            FileOutputStream fileOutputStream = new FileOutputStream(path1, true);
            fileOutputStream.write(templateCode.getBytes()); // 或者写入单个字节，如'a'
            fileOutputStream.close();
        } catch (IOException e) {
            System.out.println("写入出错了");
            System.out.println(e.getMessage());
        }
        // 读取
        try {
            FileInputStream fileInputStream = new FileInputStream(path1);
            int readData = 0;
            while ((readData = fileInputStream.read()) != -1) {
                System.out.print((char) readData);
            }
            fileInputStream.close();
        } catch (IOException e) {
            System.out.println("读取出错了");
            System.out.println(e.getMessage());
        }

        // 另一种方式
        FileInputStream fileInputStream1 = null;
        byte[] buf = new byte[8];
        int readLen = 0;
        try {
            fileInputStream1 = new FileInputStream(path1);
            while ((readLen = fileInputStream1.read(buf)) != -1) {
                System.out.println(new String(buf, 0, readLen));
            }
        } catch (IOException e) {
            System.out.println("出错了" + e.getMessage());
        } finally {
            try {
                fileInputStream1.close();
            } catch (IOException e) {
                System.out.println("文件关闭失败" + e.getMessage());
            }
        }
    }
}
