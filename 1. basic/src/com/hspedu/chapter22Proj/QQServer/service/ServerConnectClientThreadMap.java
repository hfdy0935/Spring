package com.hspedu.chapter22Proj.QQServer.service;

import java.util.Collection;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;

/**
 * 服务端维护在线用户map
 */
public class ServerConnectClientThreadMap {
    private static final HashMap<String, ServerConnectClientThread> map = new HashMap<>();

    public static void put(String userId, ServerConnectClientThread scct) {
        map.put(userId, scct);
    }

    public static ServerConnectClientThread get(String userId) {
        return map.get(userId);
    }

    public static void remove(String userId) {
        map.remove(userId);
    }

    public static boolean has(String userId) {
        return map.containsKey(userId);
    }

    public static Collection<ServerConnectClientThread> values() {
        return map.values();
    }

    public static Set<Map.Entry<String, ServerConnectClientThread>> entrySet() {
        return map.entrySet();
    }

    /**
     * 获取所有在线用户id
     *
     * @return String 用户id用空格分隔
     */
    public static String getOnlineUsers() {
        StringBuilder result = new StringBuilder();
        for (String userId : map.keySet()) {
            result.append(userId).append(" ");
        }
        // 删除最后一个空格
        result.delete(result.length() - 1, result.length());
        return result.toString();
    }
}
