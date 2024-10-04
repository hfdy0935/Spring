package com.hspedu.chapter21.hw01;

import java.io.*;
import java.net.InetAddress;
import java.net.Socket;
import java.util.Scanner;

@SuppressWarnings({"all"})
public class Client {
    private static final Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) throws IOException {
        Socket socket = new Socket(InetAddress.getLocalHost(), 9999);
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(socket.getInputStream()));
        BufferedWriter bufferedWriter = new BufferedWriter(new OutputStreamWriter(socket.getOutputStream()));
        while (true) {
            System.out.println("输入要发送给服务端的内容：");
            String line = scanner.next();
            bufferedWriter.write(line);
            bufferedWriter.newLine();
            bufferedWriter.flush(); // // 如果使用的字符流，需要手动刷新，否则数据不会写入数据通道

            String data = bufferedReader.readLine();
            System.out.println(data);
        }
    }
}
