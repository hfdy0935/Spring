package com.hspedu.chapter21;

import java.net.InetAddress;

public class InetAddressTest {
    public static void main(String[] args) throws java.net.UnknownHostException {
        InetAddress ia = InetAddress.getLocalHost();
        System.out.println(ia);
        String host = ia.getHostName();
        System.out.println(host); // DESKTOP-NH15KK3
        String address = ia.getHostAddress();
        System.out.println(address); // 172.27.39.7

        //
        System.out.println(InetAddress.getByName("www.baidu.com")); // www.baidu.com/157.148.69.80
    }
}