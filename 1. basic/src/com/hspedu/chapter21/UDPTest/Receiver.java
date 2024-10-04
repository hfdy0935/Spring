package com.hspedu.chapter21.UDPTest;

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;

public class Receiver {
    public static void main(String[] args) throws IOException {
        // 1. 创建一个DatagramSocket对象
        DatagramSocket socket = new DatagramSocket(9999);
        // 2. 构建DatagramPacket对象，准备接收数据
        byte[] buf = new byte[1024];
        DatagramPacket packet = new DatagramPacket(buf, buf.length);
        // 3. 阻塞等待，直到收到数据包
        socket.receive(packet);
        // 4. 把packet进行拆包，取出数据并显示
        int length = packet.getLength(); // 实际接收到的数据字节长度
        byte[] data = packet.getData(); // 接收到数据
        String s = new String(data, 0, length);
        System.out.println(s);

        // 5. 给发送端回复消息
        data = "好的，明天见".getBytes();
        // 封装的 DatagramPacket 对象 data 内容字节数组 , data.length , 主机(IP) , 端口
        packet = new DatagramPacket(data, data.length, InetAddress.getByName("192.168.12.1"), 9998);
        socket.send(packet);
        // 6.  关闭连接
        socket.close();
    }
}
