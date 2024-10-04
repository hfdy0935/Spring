package com.hspedu.chapter22Proj.QQClient.service;

import com.hspedu.chapter22Proj.common.Message;
import com.hspedu.chapter22Proj.common.MessageType;
import com.hspedu.chapter22Proj.common.User;

import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.InetAddress;
import java.net.Socket;

/**
 * 用户登录注册服务
 */
public class QQClient {
    private User user = new User();
    private Socket socket = null;
    private ObjectOutputStream oos = null;
    private ObjectInputStream ois = null;
    private ClientConnectServerThread clientConnectServerThread = null; // 对应的通信线程
    private UserClientService userClientService = null;
    private MessageClientService messageClientService = null;

    public QQClient() {
    }

    public UserClientService getUserClientService() {
        return userClientService;
    }

    public MessageClientService getMessageClientService() {
        return messageClientService;
    }

    /**
     * 验证用户id和密码是否合法，建立连接
     *
     * @param userId   用户id
     * @param password 密码
     * @return 是否成功
     */
    public boolean login(String userId, String password) {
        user.setUserId(userId);
        user.setPassword(password);
        // 连接服务器
        try {
            socket = new Socket(InetAddress.getByName("127.0.0.1"), 9999);
            // 发送User对象
            oos = new ObjectOutputStream(socket.getOutputStream());
            oos.writeObject(user);
            // 接收服务端返回的Message对象
            ois = new ObjectInputStream(socket.getInputStream());
            Message msg = (Message) ois.readObject();
            // 判断是否登录成功
            if (msg.getMsgType().equals(MessageType.MESSAGE_LOGIN_SUCCEED)) {
                // 初始化用户、消息服务
                userClientService = new UserClientService(user, ois, oos);
                messageClientService = new MessageClientService(user, ois, oos);
                // 创建对话线程，加入线程集合
                // 这里只需要消息服务
                clientConnectServerThread = new ClientConnectServerThread(socket, oos, ois, user, userClientService,messageClientService);
                clientConnectServerThread.start();
                // 加入客户端线程map
                ClientConnectionServerThreadMap.put(userId, clientConnectServerThread);
                System.out.println("以下为离线消息：");
                return true;
            } else {
                System.out.println(msg.getContent());
                socket.close();
                oos.close();
                ois.close();
                return false;
            }
        } catch (Exception e) {
            return false;
        }
    }
}
