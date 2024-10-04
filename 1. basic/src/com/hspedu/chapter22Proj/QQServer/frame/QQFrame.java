package com.hspedu.chapter22Proj.QQServer.frame;

import com.hspedu.chapter22Proj.QQServer.service.QQServer;
import com.hspedu.chapter22Proj.QQServer.service.SendMessageToAllUserService;

public class QQFrame {
    public static void main(String[] args) {
        new Thread(new SendMessageToAllUserService()).start();
        new QQServer();
    }
}
