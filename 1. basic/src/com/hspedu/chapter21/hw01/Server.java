package com.hspedu.chapter21.hw01;

import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;

@SuppressWarnings({"all"})
public class Server {
    public static void main(String[] args) throws IOException {
        ServerSocket serverSocket = new ServerSocket(9999);
        Socket socket = serverSocket.accept();
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(socket.getInputStream()));
        BufferedWriter bufferedWriter = new BufferedWriter(new OutputStreamWriter(socket.getOutputStream()));
        // 接收消息
        while (true) {
            String data = bufferedReader.readLine();
            if (data.equals("name")) {
                bufferedWriter.write("我是nova");
            } else if (data.equals("hobby")) {
                bufferedWriter.write("编写java程序");
            } else {
                bufferedWriter.write("你说啥呢");
            }
            bufferedWriter.newLine();
            bufferedWriter.flush();
        }
//        bufferedReader.close();
//        bufferedWriter.close();
//        serverSocket.close();
    }
}
