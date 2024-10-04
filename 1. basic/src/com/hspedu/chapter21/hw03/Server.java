package com.hspedu.chapter21.hw03;

import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;

public class Server {
    public static void main(String[] args) throws IOException {
        ServerSocket serverSocket = new ServerSocket(9999);
        Socket socket = serverSocket.accept();
        BufferedReader br = new BufferedReader(new InputStreamReader(socket.getInputStream()));
        BufferedOutputStream bos = new BufferedOutputStream(socket.getOutputStream());

        // 接收客户端消息
        String msg = br.readLine();
        String basePath = "D:\\桌面\\t\\Java\\javaPractise\\src\\com\\hspedu\\assets\\music";
        File music = new File(basePath, msg + ".mp3");
        if (!music.exists()) {
            music = new File(basePath, "无名.mp3");
        }
        BufferedInputStream bisFile = new BufferedInputStream(new FileInputStream(music));
        byte[] bytes = Utils.streamToByteArray(bisFile);
        // 给客户端返回
        bos.write(bytes);
        bos.flush();

        // 关闭
        bisFile.close();
        bos.close();
        br.close();
        socket.close();
        serverSocket.close();


    }
}
