package com.hspedu.chapter21.FileUploadTest;

import java.io.*;
import java.net.InetAddress;
import java.net.Socket;

public class Client {
    public static void main(String[] args) throws IOException {
        String sendPath = "D:\\桌面\\t\\Java\\javaPractise\\src\\com\\hspedu\\assets\\video.mp4";
        Socket socket = new Socket(InetAddress.getLocalHost(), 8888);

        BufferedInputStream bis = new BufferedInputStream(new FileInputStream(sendPath));
        byte[] bytes = Utils.streamToByteArray(bis);
        BufferedOutputStream bos = new BufferedOutputStream(socket.getOutputStream());
        bos.write(bytes);
        bis.close();
        socket.shutdownOutput();

        // 接收服务端的回复
        InputStream is = socket.getInputStream();
        String s = Utils.streamToString(is);
        System.out.println("收到服务端的响应：" + s);

        is.close();
        bos.close();
        socket.close();
    }
}
