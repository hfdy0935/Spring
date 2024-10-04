package com.hspedu.chapter21.UDPTest;

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;

public class Sender {
    public static void main(String[] args) throws IOException {
        // 1. 创建DatagramSocket对象
        DatagramSocket socket = new DatagramSocket(9998);
        // 2. 将需要发送的数据封装到DatagramPacket对象
        byte[] data = "hello 明天吃火锅".getBytes();
        DatagramPacket packet = new DatagramPacket(data, data.length, InetAddress.getByName("192.168.12.1"), 9999);
        socket.send(packet);
        // 3. 接收从接收端回复的信息
        byte[] buf = new byte[1024];
        socket.receive(packet);
        int length = packet.getLength();
        data = packet.getData();
        String s = new String(data, 0, length);
        System.out.println(s);

        // 4. 关闭连接
        socket.close();
    }
}
