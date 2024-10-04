package com.hspedu.chapter21.hw02;

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.nio.charset.StandardCharsets;

public class Receiver {
    public static void main(String[] args) throws IOException {
        DatagramSocket socket = new DatagramSocket(8888);
        byte[] buf = new byte[1024];
        DatagramPacket packet = new DatagramPacket(buf, buf.length);
        System.out.println("接收端 等待接收");
        while (true) {
            // 等待连接
            socket.receive(packet);
            // 读取数据
            int len = packet.getLength();
            byte[] data = packet.getData();
            String str = new String(data, 0, len, StandardCharsets.UTF_8);
            System.out.println(str);

            String res = "What?";
            if (str.equals("四大名著有哪些")) {
                res = "《三国演义》、《水浒传》、《红楼梦》、《西游记》";
            }
            packet = new DatagramPacket(res.getBytes(), res.getBytes(StandardCharsets.UTF_8).length, InetAddress.getLocalHost(), 9998);
            socket.send(packet);
        }
    }
}
