package com.hspedu.chapter21.socketTest;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.ServerSocket;
import java.net.Socket;

public class Server {
    public static void main(String[] args) throws IOException {
        // 1. 本机9999端口监听
        ServerSocket serverSocket = new ServerSocket(9999);
        System.out.println("服务已启动，9999端口");
        // 2. 没客户端连接时会阻塞，一直等；有连接会继续执行
        Socket socket = serverSocket.accept();
        System.out.println("服务端消息：有客户端连上服务端");
        // 3. 输入输出流
        InputStream inputStream = socket.getInputStream();
        OutputStream outputStream = socket.getOutputStream();
        // 4. IO读取
        byte[] buf = new byte[1024];
        int readLn;
        while ((readLn = inputStream.read(buf)) != -1) {
            // 根据读取到的实际长度显示内容
            System.out.println("服务端收到客户端的消息：" + new String(buf, 0, readLn));
        }
        // 服务端向客户端发送消息
        outputStream.write("{code:200,msg:\"成功\",data:\"来自服务端的数据\"}".getBytes());
        socket.shutdownOutput(); // 结束输出标记
        // 5. 关闭
        inputStream.close();
        outputStream.close();
        System.out.println();
        serverSocket.close();
        System.out.println("服务端退出");
    }
}