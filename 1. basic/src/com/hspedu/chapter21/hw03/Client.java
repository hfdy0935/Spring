package com.hspedu.chapter21.hw03;

import java.io.*;
import java.net.InetAddress;
import java.net.Socket;
import java.util.Scanner;

public class Client {
    private static Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) throws IOException {
        Socket socket = new Socket(InetAddress.getLocalHost(), 9999);
        // 向服务端发送消息
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(socket.getOutputStream()));
        String input = scanner.nextLine();
        bw.write(input);
        bw.newLine();
        bw.flush();
        // 接收响应
        BufferedInputStream bis = new BufferedInputStream(socket.getInputStream());
        byte[] bytes = Utils.streamToByteArray(bis);
        // 写入文件
        String saveMusicBasePath = "D:\\桌面\\t\\Java\\javaPractise\\src\\com\\hspedu\\assets\\music\\save.mp3";
        BufferedOutputStream fileBos = new BufferedOutputStream(new FileOutputStream(saveMusicBasePath));
        fileBos.write(bytes);
        fileBos.flush();

        // 关闭
        fileBos.close();
        bis.close();
        bw.close();
        socket.close();
    }
}
