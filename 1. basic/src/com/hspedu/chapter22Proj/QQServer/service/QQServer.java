package com.hspedu.chapter22Proj.QQServer.service;


import com.hspedu.chapter22Proj.utils.Utility;
import com.hspedu.chapter22Proj.common.Message;
import com.hspedu.chapter22Proj.common.MessageType;
import com.hspedu.chapter22Proj.common.User;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.concurrent.ConcurrentHashMap;


/**
 * 服务端
 */
public class QQServer {
    private ServerSocket serverSocket;
    private ObjectOutputStream oos;
    private ObjectInputStream ois;
    private UserServerService userServerService; // 用户服务
    private MessageServerService messageServerService; // 消息服务
    // 用户列表，线程安全
    private static final ConcurrentHashMap<String, User> validUsers = new ConcurrentHashMap<>();

    static {
        validUsers.put("100", new User("100", "123456"));
        validUsers.put("张三", new User("张三", "123456"));
        validUsers.put("李四", new User("李四", "123456"));
        validUsers.put("王五", new User("王五", "123456"));
        validUsers.put("赵六", new User("赵六", "123456"));
        validUsers.put("600", new User("100", "123456"));
    }

    public static ConcurrentHashMap<String, User> getAllUsers() {
        return validUsers;
    }

    /**
     * 判断用户是否存在
     *
     * @param userId   用户id
     * @param password 密码
     * @return boolean
     */
    public boolean isUserExists(String userId, String password) {
        User user;
        return (user = validUsers.get(userId)) != null && user.getPassword().equals(password);
    }

    /**
     * 判断用户是否已登录
     *
     * @param userId 用户id
     * @return boolean
     */
    public boolean isUserLogin(String userId) {
        return ServerConnectClientThreadMap.has(userId);
    }

    public QQServer() {
        try {
            System.out.println("服务端在9999端口监听");
            serverSocket = new ServerSocket(9999);
            // 监听所有客户端连接
            while (true) {
                // 等待客户端连接，无连接会阻塞
                Socket socket = serverSocket.accept();
                ois = new ObjectInputStream(socket.getInputStream());
                oos = new ObjectOutputStream(socket.getOutputStream());
                User user = (User) ois.readObject();
                Message message = new Message();
                // 如果用户名密码正确
                if (isUserExists(user.getUserId(), user.getPassword())) {
                    // 如果已登录
                    if (isUserLogin(user.getUserId())) {
                        String formattedDatetime = Utility.getFormattedDateTime();
                        message.setMsgType(MessageType.MESSAGE_LOGIN_FAIL);
                        message.setContent("登录失败，用户 " + user.getUserId() + " 已登录，时间：" + formattedDatetime);
                        oos.writeObject(message);
                        socket.close();
                    } else {
                        // 之前未登录，正常登录
                        message.setMsgType(MessageType.MESSAGE_LOGIN_SUCCEED);
                        message.setContent("登陆成功，欢迎用户 " + user.getUserId());
                        oos.writeObject(message);
                        // 为该用户创建服务
                        userServerService = new UserServerService(ois, oos, user.getUserId());
                        messageServerService = new MessageServerService(ois, oos, user.getUserId());
                        // 创建线程，加到集合，用这里的oos和ois，不要用线程中的socket再开，防止header错误
                        ServerConnectClientThread serverConnectClientThread = new ServerConnectClientThread(user.getUserId(), socket, oos, ois, userServerService, messageServerService);
                        serverConnectClientThread.start();
                        ServerConnectClientThreadMap.put(user.getUserId(), serverConnectClientThread);
                        System.out.println("用户 " + user.getUserId() + " 上线，时间：" + Utility.getFormattedDateTime());
                        // 离线消息
                        messageServerService.handleOfflineMessage();
                    }
                } else {
                    // 用户名密码没通过
                    message.setMsgType(MessageType.MESSAGE_LOGIN_FAIL);
                    String formattedDatetime = Utility.getFormattedDateTime();
                    System.out.println("用户名：" + user.getUserId() + " 密码：" + user.getPassword() + " 登录失败，时间：" + formattedDatetime);
                    message.setContent("登录失败，账号或密码错误，时间：" + formattedDatetime);
                    oos.writeObject(message);
                    socket.close();
                }
            }
        } catch (Exception e) {
            System.out.println(e.getMessage());
        } finally {
            try {
                serverSocket.close();
                oos.close();
                ois.close();
            } catch (IOException e) {
                System.out.println(e.getMessage());
            }
        }
    }
}
