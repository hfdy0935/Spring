package com.hspedu.chapter22Proj.QQClient.service;

import com.hspedu.chapter22Proj.common.Message;
import com.hspedu.chapter22Proj.common.MessageType;
import com.hspedu.chapter22Proj.common.User;
import com.hspedu.chapter22Proj.utils.Utility;

import java.io.*;

/**
 * 消息客户端服务
 */
public class MessageClientService {
    private final User user;
    private final ObjectInputStream ois;
    private final ObjectOutputStream oos;


    public MessageClientService(User user, ObjectInputStream ois, ObjectOutputStream oos) {
        this.user = user;
        this.ois = ois;
        this.oos = oos;
    }


    // 以下客户端发出消息的服务

    /**
     * 群发消息
     */
    public void sendGroupMessage(String content) {
        try {
            Message message = new Message();
            message.setMsgType(MessageType.MESSAGE_COMM_MES);
            message.setSender(user.getUserId());
            message.setContent(content);
            oos.writeObject(message);
            IsThreadUsed.waitSendAndReceiveMessage();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    /**
     * 私发消息
     *
     * @param targetUserId 发给谁
     * @param content      内容
     */
    public void sendPrivateMessage(String targetUserId, String content) {
        try {
            Message message = new Message();
            message.setMsgType(MessageType.MESSAGE_COMM_MES);
            message.setSender(user.getUserId());
            message.setReceiver(targetUserId);
            message.setContent(content);
            oos.writeObject(message);
            IsThreadUsed.waitSendAndReceiveMessage();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    /**
     * 发送文件
     *
     * @param receiverId 接收者id
     * @param src        目标路径
     * @param dest       源文件路径
     */
    public void sendFile(String receiverId, String src, String dest) {
        try {
            Message message = new Message();
            message.setMsgType(MessageType.MESSAGE_FILE_MSG);
            message.setSender(user.getUserId());
            message.setReceiver(receiverId);
            message.setSrc(src);
            message.setDest(dest);
            byte[] fileBytes = new byte[(int) new File(src).length()];
            FileInputStream fileInputStream = new FileInputStream(src);
            fileInputStream.read(fileBytes);
            message.setFileBytes(fileBytes);
            oos.writeObject(message);
            IsThreadUsed.waitSendAndReceiveMessage();
            fileInputStream.close();
        } catch (IOException e) {
            System.out.println("发送失败，请检查文件路径是否正确");
        }
    }

    // 以下是客户端接收到消息的服务

    /**
     * 客户端接收文件
     *
     * @param message Message
     */
    public void receiveFile(Message message) {
        Message resMessage = Message.copyFrom(message, "sender", "receiver", "src", "dest");
        try (FileOutputStream fos = new FileOutputStream(message.getDest())) {
            fos.write(message.getFileBytes());
            System.out.println("成功接收来自 " + message.getSender() + " 的文件，已存到 " + message.getDest());
        } catch (IOException e) {
            System.out.println("接收 " + message.getSender() + " 的文件失败");
        }
    }

    /**
     * 查询在线人数
     *
     * @param message Message
     */
    public void getOnlineUserNum(Message message) {
        // 查询在线人数的响应
        String[] onLineUserArr = message.getContent().split(" ");
        Utility.printTitle("在线用户列表");
        System.out.println("\t序号\t\t用户id");
        for (int i = 0; i < onLineUserArr.length; i++) {
            String currUserId = onLineUserArr[i];
            System.out.println("\t" + (i + 1) + "\t\t" + currUserId + (currUserId.equals(user.getUserId()) ? "\t\t我" : ""));
        }
        IsThreadUsed.setIsUsed(false); // 接收完毕
    }


    /**
     * 收到普通消息
     *
     * @param message Message
     */
    public void getCommonMessage(Message message) {
        // 普通消息
        String t = message.getReceiver() == null ? "大家伙儿" : message.getReceiver() + "（我）";
        System.out.println(message.getSender() + " 对 " + t + " 说：" + message.getContent());
        IsThreadUsed.setIsUsed(false); // 接收完毕
    }

    /**
     * 消息发送失败
     *
     * @param message Message
     */
    public void sendMessageFail(Message message) {
        // 消息发送失败，发送消息的接收方不存在或不在线
        System.out.println(message.getContent());
        IsThreadUsed.setIsUsed(false); // 接收完毕
    }

    /**
     * 消息发送成功
     *
     * @param message Message
     */
    public void sendMessageSuccess(Message message) {
        String tt = message.getReceiver() == null ? "大家伙儿 " : message.getReceiver();
        System.out.println(user.getUserId() + "（我）" + " 对 " + tt + "说：" + message.getContent());
        IsThreadUsed.setIsUsed(false); // 接收完毕
    }

    /**
     * 文件发送成功
     *
     * @param message Message
     */
    public void sendFileSuccess(Message message) {
        System.out.println("成功把 " + message.getSrc() + " 发送到 " + message.getReceiver() + " 的 " + message.getDest());
        IsThreadUsed.setIsUsed(false); // 接收完毕
    }

    /**
     * 文件按发送失败
     */
    public void sendFileFail() {
        System.out.println("发送失败");
        IsThreadUsed.setIsUsed(false); // 接收完毕
    }

    /**
     * 还未处理的情况
     */
    public void notImplementsCondition() {
        System.out.println("暂未处理");
        IsThreadUsed.setIsUsed(false); // 接收完毕
    }
}
