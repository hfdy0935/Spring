package com.hspedu.chapter19;

import java.io.*;

public class BufferedReaderWriterTest {
    public static void main(String[] args) throws IOException {
        // 把FileRWTest.tsx的内容复制到BufferRW.tsx
        String basepath = "D:\\桌面\\t\\Java\\javaPractise\\src\\com\\hspedu\\assets";
        String sourceFilename = "FileRWTest.tsx";
        File sourceFile = new File(basepath, sourceFilename);
        BufferedReader bufferedReader = new BufferedReader(new FileReader(sourceFile));

        String targetFilename = "BufferRWTest.tsx";
        File targetFile = new File(basepath, targetFilename);
        if (!targetFile.exists()) {
            targetFile.createNewFile();
        }
        BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter(targetFile));

        //
        String line;
        while ((line = bufferedReader.readLine()) != null) {
            bufferedWriter.write(line);
            bufferedWriter.newLine();
        }
        bufferedReader.close();
        bufferedWriter.close();
    }
}
