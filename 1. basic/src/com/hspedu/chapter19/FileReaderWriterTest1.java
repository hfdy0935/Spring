package com.hspedu.chapter19;

import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

public class FileReaderWriterTest1 {
    public static void main(String[] args) throws IOException {
        // 把之前fileIOTest.tsx的内容写进fileRWTest.txt中
        String basePath = "D:\\桌面\\t\\Java\\javaPractise\\src\\com\\hspedu\\assets";
        String sourceFilename = "fileIOTest.tsx";
        File sourceFile = new File(basePath, sourceFilename);
        FileReader fileReader = new FileReader(sourceFile);

        String targetFilename = "fileRWTest.tsx";
        File targetFile = new File(basePath, targetFilename);
        if (!targetFile.exists()) {
            targetFile.createNewFile();
        }
        FileWriter fileWriter = new FileWriter(targetFile);
        int data;
        while ((data = fileReader.read()) != -1) {
            fileWriter.write(data); // 写入单个字符
        }
        fileWriter.write("\n//写入成功"); // 写入字符串

        // 关闭连接
        fileReader.close();
        fileWriter.close();
    }
}
