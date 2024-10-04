package com.hspedu.chapter22Proj.QQServer.service;

import com.hspedu.chapter22Proj.common.Message;
import com.hspedu.chapter22Proj.common.MessageType;
import com.hspedu.chapter22Proj.common.User;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/**
 * 消息服务端服务
 */
public class MessageServerService {
    private final ObjectInputStream ois;
    private final ObjectOutputStream oos;
    private final String userId;

    public MessageServerService(ObjectInputStream ois, ObjectOutputStream oos, String userId) {
        this.ois = ois;
        this.oos = oos;
        this.userId = userId;
    }


    /**
     * 处理普通消息
     */
    public void handleCommonMessage(Message reqMessage) throws IOException {
        Message resMessage = Message.copyFrom(reqMessage, "sender", "content");
        // 普通消息
        resMessage.setMsgType(MessageType.MESSAGE_COMM_MES);
        if (reqMessage.getReceiver() == null) {
            // 如果没收信人，表示群发
            for (Map.Entry<String, ServerConnectClientThread> entry : ServerConnectClientThreadMap.entrySet()) {
                if (entry.getKey().equals(userId)) {
                    // 不给本人发
                    continue;
                }
                ServerConnectClientThread scct = entry.getValue();
                scct.getOos().writeObject(reqMessage);
            }
            // 给本人发 发送成功的响应
            resMessage.setMsgType(MessageType.MESSAGE_RES_SEND_SUCCESS);
            oos.writeObject(resMessage);
            String receiver = reqMessage.getReceiver() == null ? "大家伙儿" : reqMessage.getReceiver();
            System.out.println("用户 " + reqMessage.getSender() + " 对 " + receiver + " 说：" + reqMessage.getContent());
        } else {
            // 私聊消息
            // 首先检查用户是否存在
            ConcurrentHashMap<String, User> allUsers = QQServer.getAllUsers();
            String receiverId = reqMessage.getReceiver();
            // 如果没有该用户
            if (!allUsers.containsKey(receiverId)) {
                resMessage.setMsgType(MessageType.MESSAGE_RES_USER_NOT_EXIST);
                resMessage.setContent("发送失败，用户 " + reqMessage.getReceiver() + " 不存在");
                // 发给消息发送者
                ServerConnectClientThreadMap.get(reqMessage.getSender()).getOos().writeObject(resMessage);
            } else {
                resMessage.setReceiver(reqMessage.getReceiver());
                resMessage.setMsgType(MessageType.MESSAGE_RES_SEND_SUCCESS);
                // 在线
                if (ServerConnectClientThreadMap.has(receiverId)) {
                    ServerConnectClientThreadMap.get(reqMessage.getReceiver()).getOos().writeObject(reqMessage);
                } else {
                    // 添加到离线消息列表
                    OfflineMessageMap.add(reqMessage.getReceiver(), reqMessage);
                }
                // 发送者
                ServerConnectClientThreadMap.get(reqMessage.getSender()).getOos().writeObject(resMessage);
                System.out.println("用户 " + reqMessage.getSender() + " 对 " + reqMessage.getReceiver() + " 说：" + reqMessage.getContent());
            }
        }
    }

    /**
     * 处理发送文件消息过程
     *
     * @param reqMessage 文件消息
     */
    public void handleSendFileMessage(Message reqMessage) {
        Message resMessage = new Message();
        try {
            // 首先检查用户是否存在
            ConcurrentHashMap<String, User> allUsers = QQServer.getAllUsers();
            String receiverId = reqMessage.getReceiver();
            // 不存在
            if (!allUsers.containsKey(receiverId)) {
                resMessage.setMsgType(MessageType.MESSAGE_RES_USER_NOT_EXIST);
                resMessage.setContent("发送失败，用户 " + reqMessage.getReceiver() + " 不存在");
                // 发送失败消息给文件发送者
                oos.writeObject(reqMessage);
            } else {
                resMessage.setReceiver(reqMessage.getReceiver());
                resMessage.setMsgType(MessageType.MESSAGE_RES_SEND_SUCCESS);
                // 在线
                if (ServerConnectClientThreadMap.has(receiverId)) {
                    // 把文件消息转发给receiver
                    ServerConnectClientThreadMap.get(reqMessage.getReceiver()).getOos().writeObject(reqMessage);
                } else {
                    // 添加到离线消息列表
                    OfflineMessageMap.add(reqMessage.getReceiver(), reqMessage);
                }
                // 发给发送者
                resMessage.setSrc(reqMessage.getSrc());
                resMessage.setDest(reqMessage.getDest());
                resMessage.setMsgType(MessageType.MESSAGE_RECEIVE_FILE_SUCCESS);
                oos.writeObject(resMessage);
            }
        } catch (Exception e) {
            try {
                // 出错直接给发送方返回接收失败
                resMessage.setMsgType(MessageType.MESSAGE_RECEIVE_FILE_FAIL);
                resMessage.setContent("发送失败");
                ServerConnectClientThreadMap.get(reqMessage.getSender()).getOos().writeObject(resMessage);
            } catch (Exception e1) {
                throw new RuntimeException(e1);
            }
        }
    }

    /**
     * 处理离线消息
     */
    public void handleOfflineMessage() {
        for (Message msg : OfflineMessageMap.get(userId)) {
            try {
                oos.writeObject(msg);
            } catch (IOException e) {
                System.out.println("发送离线消息失败");
            }
            OfflineMessageMap.clear(userId);
        }
    }
}
