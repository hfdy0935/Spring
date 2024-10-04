package com.hspedu.chapter22Proj.common;

import java.io.Serializable;
import java.util.Arrays;
import java.util.List;

/**
 * 消息
 */
public class Message implements Serializable {
    private static final long serialVersionUID = 1L;
    private String sender; // 发送者id
    private String receiver; // 接收者id
    private String content; // 消息内容
    private String sendTime; // 发送时间
    private String msgType; // 消息类型

    private byte[] fileBytes;
    private int fileLen;
    private String dest;
    private String src;

    public Message() {
    }

    public static Message copyFrom(Message src) {
        Message msg = new Message();
        msg.setSender(src.getSender());
        msg.setReceiver(src.getReceiver());
        msg.setContent(src.getContent());
        msg.setSendTime(src.getSendTime());
        msg.setMsgType(src.getMsgType());
        msg.setFileBytes(src.getFileBytes());
        msg.setFileLen(src.getFileLen());
        msg.setDest(src.getDest());
        msg.setSrc(src.getSrc());
        return msg;
    }

    public static Message copyFrom(Message src, String... props) {
        Message result = new Message();
        List<String> list = Arrays.asList(props);
        if (list.contains("sender")) {
            result.setSender(src.getSender());
        }
        if (list.contains("receiver")) {
            result.setReceiver(src.getReceiver());
        }
        if (list.contains("content")) {
            result.setContent(src.getContent());
        }
        if (list.contains("sendTime")) {
            result.setSendTime(src.getSendTime());
        }
        if (list.contains("msgType")) {
            result.setMsgType(src.getMsgType());
        }
        if (list.contains("fileBytes")) {
            result.setFileBytes(src.getFileBytes());
        }
        if (list.contains("fileLen")) {
            result.setFileLen(src.getFileLen());
        }
        if (list.contains("dest")) {
            result.setDest(src.getDest());
        }
        if (list.contains("src")) {
            result.setSrc(src.getSrc());
        }
        return result;
    }

    public String getSender() {
        return sender;
    }

    public void setSender(String sender) {
        this.sender = sender;
    }

    public String getReceiver() {
        return receiver;
    }

    public void setReceiver(String receiver) {
        this.receiver = receiver;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getSendTime() {
        return sendTime;
    }

    public void setSendTime(String sendTime) {
        this.sendTime = sendTime;
    }

    public String getMsgType() {
        return msgType;
    }

    public void setMsgType(String msgType) {
        this.msgType = msgType;
    }

    public byte[] getFileBytes() {
        return fileBytes;
    }

    public void setFileBytes(byte[] fileBytes) {
        this.fileBytes = fileBytes;
    }

    public int getFileLen() {
        return fileLen;
    }

    public void setFileLen(int fileLen) {
        this.fileLen = fileLen;
    }

    public String getDest() {
        return dest;
    }

    public void setDest(String dest) {
        this.dest = dest;
    }

    public String getSrc() {
        return src;
    }

    public void setSrc(String src) {
        this.src = src;
    }
}
