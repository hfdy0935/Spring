package com.hspedu.chapter22Proj.QQServer.service;

import com.hspedu.chapter22Proj.common.Message;

import java.util.Vector;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentMap;

/**
 * 离线消息
 */
public class OfflineMessageMap {
    private static ConcurrentMap<String, Vector<Message>> map = new ConcurrentHashMap<>();

    /**
     * 添加离线消息
     *
     * @param userId  userId
     * @param message Message
     */
    public static void add(String userId, Message message) {
        if (map.containsKey(userId)) {
            map.get(userId).add(message);
        } else {
            Vector<Message> messages = new Vector<>();
            messages.add(message);
            map.put(userId, messages);
        }
    }

    /**
     * 获取该用户的所有消息
     *
     * @param userId userId
     * @return Vector<Message>
     */
    public static Vector<Message> get(String userId) {
        Vector<Message> messages = map.get(userId);
        if (messages != null) {
            return messages;
        }
        return new Vector<>();
    }

    /**
     * 清空用户的离线消息
     *
     * @param userId userId
     */
    public static void clear(String userId) {
        Vector<Message> messages = map.get(userId);
        if (messages != null) {
            map.remove(userId);
        }
    }
}
