package com.hspedu.chapter19;

import java.io.*;

public class BufferedInputOutputStreamTest {
    public static void main(String[] args) throws IOException {
        String basePath = "D:\\桌面\\t\\Java\\javaPractise\\src\\com\\hspedu\\assets";
        String sourceFilename = "video.mp4";
        File sourceFile = new File(basePath, sourceFilename);
        BufferedInputStream sourceBufferInputStream = new BufferedInputStream(new FileInputStream(sourceFile));

        String targetFilename = "video_copy1.mp4";
        File targetFile = new File(basePath, targetFilename);
        BufferedOutputStream targetBufferOutputStream = new BufferedOutputStream(new FileOutputStream(targetFile));
        //
        int data;
        while ((data = sourceBufferInputStream.read()) != -1) {
            targetBufferOutputStream.write(data);
        }
        sourceBufferInputStream.close();
        targetBufferOutputStream.close();
    }
}
