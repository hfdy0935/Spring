package com.hspedu.chapter22Proj.QQServer.service;

import com.hspedu.chapter22Proj.common.Message;
import com.hspedu.chapter22Proj.common.MessageType;
import com.hspedu.chapter22Proj.utils.Utility;

import java.io.IOException;

/**
 * 服务器主动推送消息
 */
public class SendMessageToAllUserService implements Runnable {
    @Override
    public void run() {
        System.out.println("输入要想所有用户推送的消息：");
        Message message = new Message();
        message.setSender("服务端");
        message.setContent(Utility.readString(50));
        message.setMsgType(MessageType.MESSAGE_COMM_MES);
        try {
            for (ServerConnectClientThread scct : ServerConnectClientThreadMap.values()) {
                scct.getOos().writeObject(message);
            }
        } catch (IOException e) {
            System.out.println("推送失败");
        }
    }
}
