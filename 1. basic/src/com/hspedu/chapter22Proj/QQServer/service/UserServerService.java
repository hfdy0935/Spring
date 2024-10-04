package com.hspedu.chapter22Proj.QQServer.service;

import com.hspedu.chapter22Proj.common.Message;
import com.hspedu.chapter22Proj.common.MessageType;
import com.hspedu.chapter22Proj.utils.Utility;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;

/**
 * 用户服务端服务
 */
public class UserServerService {
    private final ObjectInputStream ois;
    private final ObjectOutputStream oos;
    private final String userId;

    public UserServerService(ObjectInputStream ois, ObjectOutputStream oos, String userId) {
        this.ois = ois;
        this.oos = oos;
        this.userId = userId;
    }

    /**
     * 获取在线用户
     *
     * @throws IOException IOException
     */
    public void getOnlineUserNum(Message reqMessage) throws IOException {
        Message resMessage = new Message();
        // 如果是请求在线人数
        System.out.println("用户 " + userId + " 获取了在线用户列表，时间：" + Utility.getFormattedDateTime());
        String onlineUsers = ServerConnectClientThreadMap.getOnlineUsers();
        resMessage.setMsgType(MessageType.MESSAGE_RES_ONLINE_USER_NUM);
        resMessage.setContent(onlineUsers);
        resMessage.setReceiver(reqMessage.getSender());
        oos.writeObject(resMessage);
    }

    /**
     * 当前用户退出登录
     */
    public void logout() throws IOException {
        Message resMessage = new Message();
        ServerConnectClientThreadMap.remove(userId);
        System.out.println("用户 " + userId + " 下线，时间：" + Utility.getFormattedDateTime());
        resMessage.setMsgType(MessageType.MESSAGE_RES_LOGOUT_SUCCESS);
        oos.writeObject(resMessage);
    }
}
