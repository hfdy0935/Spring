package com.hspedu.chapter22Proj.QQClient.view;

import com.hspedu.chapter22Proj.QQClient.service.MessageClientService;
import com.hspedu.chapter22Proj.QQClient.service.QQClient;
import com.hspedu.chapter22Proj.QQClient.service.UserClientService;
import com.hspedu.chapter22Proj.common.User;
import com.hspedu.chapter22Proj.utils.Utility;

/**
 * 客户端界面
 */
public class QQView {
    private boolean mainLoop = true; // 是否显示主界面菜单
    private String key = ""; // 接收用户菜单选择输入
    private User user = new User(); // 当前用户
    private final QQClient qqClient = new QQClient(); // 用户登录
    private UserClientService userClientService = null;
    private MessageClientService messageClientService = null;

    public static void main(String[] args) {
        new QQView().showMainView();
        System.out.println("客户端退出系统");
    }

    /**
     * 显示主菜单
     */
    private void showMainView() {

        while (mainLoop) {
            Utility.printTitle("网络通信系统");
            System.out.println("\t\t 1 登录系统");
            System.out.println("\t\t 9 退出系统");
            System.out.println("请选择操作：");
            key = Utility.readString(1);
            switch (key) {
                case "1":
                    login();
                    break;
                case "9":
                    mainLoop = false;
                    break;
                default:
                    System.out.println("输入有误，请重新选择");
                    break;
            }
        }
    }

    /**
     * 登录界面
     */
    private void login() {
        String userId, password;
        Utility.printTitle("登录");
        System.out.println("输入用户id：");
        userId = Utility.readString(50);
        System.out.println("输入密码：");
        password = Utility.readString(50);
        // 登录成功
        if (qqClient.login(userId, password)) {
            user.setUserId(userId);
            user.setPassword(password);
            // 登录之后就有用户信息了，可以初始化用户、消息服务类了
            // 记得写在二级菜单前面，不然二级菜单中获取不到
            userClientService = qqClient.getUserClientService();
            messageClientService = qqClient.getMessageClientService();
            showDashBoard();
        }
    }

    /**
     * 登录之后的界面
     */
    private void showDashBoard() {
        Utility.printTitle("欢迎用户 " + user.getUserId());
        while (true) {
            Utility.printTitle("网络通信二级菜单");
            System.out.println("\t\t 1 显示在线用户");
            System.out.println("\t\t 2 群发消息");
            System.out.println("\t\t 3 私聊消息");
            System.out.println("\t\t 4 发送文件");
            System.out.println("\t\t 8 退出登录");
            System.out.println("\t\t 9 退出系统");
            System.out.println("请选择操作：");
            key = Utility.readString(1);
            switch (key) {
                case "1":
                    userClientService.getOnlineUser();
                    break;
                case "2":
                    sendGroupMessage();
                    break;
                case "3":
                    sendPrivateMessage();
                    break;
                case "4":
                    sendFile();
                    break;
                case "8":
                    user = new User();
                    userClientService.logout();
                    return;
                case "9":
                    user = new User();
                    userClientService.logout();
                    mainLoop = false;
                    return;
                default:
                    System.out.println("输入有误，请重新选择");
            }
        }
    }

    /**
     * 群发消息
     */
    private void sendGroupMessage() {
        Utility.printTitle("群发消息");
        System.out.println("输入消息：");
        String content = Utility.readString(50);
        messageClientService.sendGroupMessage(content);
    }

    /**
     * 私聊消息
     */
    private void sendPrivateMessage() {
        Utility.printTitle("私聊消息");
        System.out.println("输入要发送消息的用户id：");
        String targetUserId = Utility.readString(50);
        System.out.println("输入消息：");
        String content = Utility.readString(50);
        if (targetUserId.equals(user.getUserId())) {
            System.out.println("发送失败，不能给自己发消息");
            return;
        }
        messageClientService.sendPrivateMessage(targetUserId, content);
    }

    /**
     * 发送文件
     */
    private void sendFile() {
        System.out.println("输入要发送的用户id：");
        String userId = Utility.readString(50);
        System.out.println("输入要发送文件的路径（如：E:\\test\\test.png）");
        String src = Utility.readString(50);
        System.out.println("输入要保存到对方电脑的地址：");
        String dest = Utility.readString(50);
        if (userId.equals(user.getUserId())) {
            System.out.println("发送失败，不能给自己发送文件");
            return;
        }
        messageClientService.sendFile(userId, src, dest);
    }
}