package com.hspedu.chapter21.socketTest;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.InetAddress;
import java.net.Socket;

public class Client {
    public static void main(String[] args) throws IOException {
        // 1. 连接服务端
        Socket socket = new Socket(InetAddress.getLocalHost(), 9999);
        System.out.println("客户端消息：客户端连上了服务端");
        // 2. 输入输出流
        InputStream inputStream = socket.getInputStream();
        OutputStream outputStream = socket.getOutputStream();
        // 3. 发收消息
        outputStream.write("{code:200,msg:\"获取成功\",data:\"来自客户端的数据\"}".getBytes());
        socket.shutdownOutput(); // 结束输出标记
        int readLen;
        byte[] buf = new byte[1024];
        while ((readLen = inputStream.read(buf)) != -1) {
            System.out.println("客户端收到服务端的消息：" + new String(buf, 0, readLen));
        }
        // 4. 关闭连接
        inputStream.close();
        outputStream.close();
        socket.close();
        System.out.println("客户端退出");
    }
}
