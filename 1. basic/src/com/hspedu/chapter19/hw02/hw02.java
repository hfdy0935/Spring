package com.hspedu.chapter19.hw02;

import java.io.*;

@SuppressWarnings({"all"})
public class hw02 {
    public static void main(String[] args) throws IOException {
        String path = "D:\\桌面\\t\\Java\\javaPractise\\src\\com\\hspedu\\assets\\hw02.txt";
        // 初始化
        // 保存为gbk
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(new FileOutputStream(path), "gbk"));
        bw.write("第一行\n第二行\n第三行\n第四行\n第五行\n第六行\n第七行\n");
        bw.close();
        // 每行加上分号
        // 如果原来用了gbk8，这里用gbk读取
        BufferedReader br = new BufferedReader(new InputStreamReader(new FileInputStream(path), "gbk"));
        String line;
        StringBuilder sb = new StringBuilder();
        while ((line = br.readLine()) != null) {
            String str = line + ";\n";
            sb.append(str);
        }
        br.close();
        System.out.println(sb.toString());
        // 再用gbk写入
        OutputStreamWriter osw1 = new OutputStreamWriter(new FileOutputStream(path), "gbk");
        osw1.write(sb.toString());
        osw1.close();
        System.out.println(sb.toString());
    }
}
