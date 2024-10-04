package com.hspedu.chapter22Proj.QQClient.service;

/**
 * 线程是否正在使用，用于协调客户端获取的服务端响应和客户端二级菜单显示的先后顺序
 */
public class IsThreadUsed {
    private static boolean isUsed = true;

    public static boolean getIsUsed() {
        return isUsed;
    }

    public static void setIsUsed(boolean newIsUsed) {
        isUsed = newIsUsed;
    }

    /**
     * 等待收发消息完毕
     */
    public static void waitSendAndReceiveMessage() {
        // 如果消息没接收完就等待
        while (IsThreadUsed.getIsUsed()) {
            // 这里需要打印一下空字符串，不然似乎显示不出来菜单...
            System.out.print("");
        }
        // 再设置为true，便于下次接收
        IsThreadUsed.setIsUsed(true);
    }
}
