package com.hspedu.chapter22Proj.QQClient.service;

import com.hspedu.chapter22Proj.common.User;
import com.hspedu.chapter22Proj.common.Message;
import com.hspedu.chapter22Proj.common.MessageType;

import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;

public class ClientConnectServerThread extends Thread {
    private final Socket socket;
    private final ObjectInputStream ois;
    private final ObjectOutputStream oos;
    private final User user;
    private final UserClientService userClientService;
    private final MessageClientService messageClientService;

    public ClientConnectServerThread(Socket socket, ObjectOutputStream oos, ObjectInputStream ois, User user, UserClientService userClientService, MessageClientService messageClientService) {
        this.socket = socket;
        this.oos = oos;
        this.ois = ois;
        this.user = user;
        this.userClientService = userClientService;
        this.messageClientService = messageClientService;
    }

    public Socket getSocket() {
        return socket;
    }


    @Override
    public void run() {
//        System.out.println("客户端线程，等待从服务端发送的消息");
        while (true) {
            try {
                // 等待服务端发送消息
                Message message = (Message) ois.readObject();
                switch (message.getMsgType()) {
                    case MessageType.MESSAGE_RES_ONLINE_USER_NUM:
                        // 查询在线人数的响应
                        messageClientService.getOnlineUserNum(message);
                        break;
                    case MessageType.MESSAGE_RES_LOGOUT_SUCCESS:
                        // 下线
                        userClientService.resLogout();
                        return;
                    case MessageType.MESSAGE_COMM_MES:
                        // 普通消息
                        messageClientService.getCommonMessage(message);
                        break;
                    case MessageType.MESSAGE_RES_USER_NOT_EXIST:
                        // 消息发送失败，发送消息的接收方不存在或不在线
                        messageClientService.sendMessageFail(message);
                        break;
                    case MessageType.MESSAGE_RES_SEND_SUCCESS:
                        // 消息发送成功;
                        messageClientService.sendMessageSuccess(message);
                        break;
                    case MessageType.MESSAGE_FILE_MSG:
                        // 接收到文件消息
                        messageClientService.receiveFile(message);
                        break;
                    case MessageType.MESSAGE_RECEIVE_FILE_SUCCESS:
                        // 文件发送成功
                        messageClientService.sendFileSuccess(message);
                        break;
                    case MessageType.MESSAGE_RECEIVE_FILE_FAIL:
                        // 文件发送失败
                        messageClientService.sendFileFail();
                        break;
                    default:
                        // 暂未处理
                        messageClientService.notImplementsCondition();
                        break;
                }
            } catch (Exception e) {
                // 服务端断开连接了，客户端强制下线
                System.out.println("服务端已断开...");
                System.exit(0);
            }
        }
    }
}
