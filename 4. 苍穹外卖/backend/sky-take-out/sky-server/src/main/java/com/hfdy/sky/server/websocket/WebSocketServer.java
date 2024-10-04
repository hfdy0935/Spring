package com.hfdy.sky.server.websocket;

import jakarta.websocket.OnClose;
import jakarta.websocket.OnMessage;
import jakarta.websocket.OnOpen;
import jakarta.websocket.Session;
import jakarta.websocket.server.PathParam;
import jakarta.websocket.server.ServerEndpoint;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

/**
 * @author hf-dy
 * @date 2024/10/3 12:10
 */

@Component
@ServerEndpoint("/ws/{sid}")
@Slf4j
public class WebSocketServer {

    // 会话对象
    private static final Map<String, Session> sessionMap = new HashMap<>();

    @OnOpen
    public void onOpen(Session session, @PathParam("sid") String sid) {
        log.info("客户端：{} 建立连接", sid);
        sessionMap.put(sid, session);
    }

    @OnMessage
    public void onMessage(String message, @PathParam("sid") String sid) {
        log.info("收到客户端：{} 的信息", sid);
    }

    @OnClose
    public void onClose(@PathParam("sid") String sid) {
        log.info("连接断开：{}", sid);
        Session session = sessionMap.get(sid);
        try {
            session.close();
            sessionMap.remove(sid);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

    }

    public void sendToAllClient(String message) {
        Collection<Session> sessions = sessionMap.values();
        sessions.forEach(session -> {
            try {
                session.getBasicRemote().sendText(message);
            } catch (Exception e) {
                log.error(e.getMessage());
            }
        });
    }
}
