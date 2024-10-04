package com.hspedu.chapter22Proj.QQServer.service;

import com.hspedu.chapter22Proj.QQClient.service.IsThreadUsed;
import com.hspedu.chapter22Proj.utils.Utility;
import com.hspedu.chapter22Proj.common.Message;
import com.hspedu.chapter22Proj.common.MessageType;

import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;

/**
 * 服务端和客户端连接的线程
 */
public class ServerConnectClientThread extends Thread {
    private final Socket socket;
    private final String userId;
    private final ObjectOutputStream oos;
    private final ObjectInputStream ois;
    private final UserServerService userServerService; // 用户服务
    private final MessageServerService messageServerService; // 消息服务

    public ServerConnectClientThread(String userId, Socket socket, ObjectOutputStream oos, ObjectInputStream ois, UserServerService userServerService, MessageServerService messageServerService) {
        this.socket = socket;
        this.userId = userId;
        this.oos = oos;
        this.ois = ois;
        this.userServerService = userServerService;
        this.messageServerService = messageServerService;
    }

    public ObjectOutputStream getOos() {
        return oos;
    }

    public ObjectInputStream getOis() {
        return ois;
    }


    @Override
    public void run() {
        while (true) {
            try {
                Message reqMessage = (Message) this.ois.readObject(); // 客户端请求消息
                // 必须把reqMessage传给对应函数，这里获取了后面就读不到了
                switch (reqMessage.getMsgType()) {
                    case MessageType.MESSAGE_REQ_ONLINE_USER_NUM:
                        // 如果是请求在线人数
                        userServerService.getOnlineUserNum(reqMessage);
                        break;
                    case MessageType.MESSAGE_REQ_LOGOUT:
                        // 退出登录
                        userServerService.logout();
                        // 这里只能结束线程，不能结束服务端整个进程
                        return;
                    case MessageType.MESSAGE_COMM_MES:
                        // 普通消息
                        messageServerService.handleCommonMessage(reqMessage);
                        break;
                    case MessageType.MESSAGE_FILE_MSG:
                        // 文件消息
                        messageServerService.handleSendFileMessage(reqMessage);
                        break;
                    case MessageType.MESSAGE_GET_OFFLINE_MSG:
                        // 获取离线消息
                        messageServerService.handleOfflineMessage();
                        IsThreadUsed.setIsUsed(false);
                        break;
                    default:
                        System.out.println("暂未处理");
                        break;
                }
            } catch (Exception e) {
                // 客户端直接退了，则退出线程
                ServerConnectClientThreadMap.remove(userId);
                System.out.println("用户 " + userId + " 下线，时间：" + Utility.getFormattedDateTime());
                return;
            }
        }
    }
}
