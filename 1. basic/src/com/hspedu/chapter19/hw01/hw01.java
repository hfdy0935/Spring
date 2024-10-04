package com.hspedu.chapter19.hw01;

import java.io.*;

public class hw01 {
    public static void main(String[] args) throws IOException {
        String path1 = "E:///mytemp";
        File file1 = new File(path1);
        // (1)
        if (!file1.exists()) {
            file1.mkdir();
        }
        // (2)
        File file2 = new File(path1, "hello.txt");
        if (file2.exists()) {
            System.out.println("已存在，无需创建");
        } else {
            file2.createNewFile();
        }
        BufferedWriter bw = new BufferedWriter(new FileWriter(file2));
        bw.write("hello,world~");
        bw.close();


//        File test = new File("./t.txt");
//        FileReader fr = new FileReader(test);
//        int i;
//        while ((i = fr.read()) != -1) {
//            System.out.print((char) i);
//        }

    }
}
