package com.hspedu.chapter21.hw02;

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.nio.charset.StandardCharsets;
import java.util.Scanner;

public class Sender {
    public static void main(String[] args) throws IOException {
        DatagramSocket socket = new DatagramSocket(9998);
        // 发送消息
        Scanner scanner = new Scanner(System.in);
        while (true) {
            System.out.println("输入问题：");
            String input = scanner.nextLine();
            DatagramPacket packet = new DatagramPacket(input.getBytes(), input.getBytes(StandardCharsets.UTF_8).length, InetAddress.getLocalHost(), 8888);
            socket.send(packet);
            System.out.println(111);
            // 接收接收端的回复
            byte[] buf = new byte[1024];
            packet = new DatagramPacket(buf, buf.length);
            socket.receive(packet);
            int len = packet.getLength();
            byte[] data1 = packet.getData();
            String s = new String(data1, 0, len, StandardCharsets.UTF_8);
            System.out.println(s);
        }
    }
}
