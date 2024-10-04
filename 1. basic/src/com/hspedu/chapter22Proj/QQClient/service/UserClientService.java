package com.hspedu.chapter22Proj.QQClient.service;

import com.hspedu.chapter22Proj.common.Message;
import com.hspedu.chapter22Proj.common.MessageType;
import com.hspedu.chapter22Proj.common.User;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;

/**
 * 用户客户端服务
 */
public class UserClientService {
    private final User user;
    private final ObjectInputStream ois;
    private final ObjectOutputStream oos;

    public UserClientService(User user, ObjectInputStream ois, ObjectOutputStream oos) {
        this.user = user;
        this.ois = ois;
        this.oos = oos;
    }


    /**
     * 发送请求，获取在线用户列表
     */
    public void getOnlineUser() {
        Message message = new Message();
        message.setMsgType(MessageType.MESSAGE_REQ_ONLINE_USER_NUM);
        message.setSender(user.getUserId());
        try {
            oos.writeObject(message);
        } catch (IOException e) {
            System.out.println("获取失败");
        }
        IsThreadUsed.waitSendAndReceiveMessage();
    }

    /**
     * 发送请求，退出登录
     */
    public void logout() {
        Message message = new Message();
        message.setMsgType(MessageType.MESSAGE_REQ_LOGOUT);
        message.setSender(user.getUserId());
        try {
            oos.writeObject(message);
            IsThreadUsed.waitSendAndReceiveMessage();
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }
    }

    /**
     * 接收响应退出登录
     */
    public void resLogout() {
        System.out.println("已退出登录");
        IsThreadUsed.setIsUsed(false);
    }
}
