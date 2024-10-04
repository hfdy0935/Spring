package com.hspedu.chapter21.FileUploadTest;

import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;

public class Server {
    public static void main(String[] args) throws IOException {
        ServerSocket serverSocket = new ServerSocket(8888);
        Socket socket = serverSocket.accept();

        OutputStream outputStream = socket.getOutputStream();

        // 读取客户端发送的数据
        BufferedInputStream bis = new BufferedInputStream(socket.getInputStream());
        byte[] bytes = Utils.streamToByteArray(bis);
        // 写入
        String savePath = "D:\\桌面\\t\\Java\\javaPractise\\src\\com\\hspedu\\assets\\tcp-trans.mp4";
        BufferedOutputStream bos = new BufferedOutputStream(new FileOutputStream(savePath));
        bos.write(bytes);
        bos.close();
        // 回复客户端
        BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(outputStream));
        writer.write("收到图片");
        writer.flush();
        socket.shutdownOutput();

        writer.close();
        bis.close();
        socket.close();
        serverSocket.close();
    }
}
