package com.hspedu.chapter22Proj.QQClient.service;

import java.util.HashMap;

public class ClientConnectionServerThreadMap {
    // key：用户id
    private static final HashMap<String, ClientConnectServerThread> map = new HashMap<>();

    /**
     * 根据userId获取通信线程
     * @param userId 用户id
     * @return 通信线程
     */
    public static ClientConnectServerThread get(String userId) {
        return map.get(userId);
    }

    /**
     * 根据用户id添加对应的客户端服务线程
     *
     * @param userId 用户id
     * @param ccst   ClientConnectServerThread实例
     */
    public static void put(String userId, ClientConnectServerThread ccst) {
        map.put(userId, ccst);
    }

    /**
     * 某个用户退出登录，线程集合中移除
     *
     * @param userId 用户id
     */
    public static void remove(String userId) {
        map.remove(userId);
    }


}
