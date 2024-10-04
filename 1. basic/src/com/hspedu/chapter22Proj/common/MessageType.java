package com.hspedu.chapter22Proj.common;

/**
 * 消息类型
 */
public interface MessageType {
    String MESSAGE_LOGIN_SUCCEED = "1"; // 登录成功
    String MESSAGE_LOGIN_FAIL = "2"; // 登录失败
    String MESSAGE_COMM_MES = "3"; // 普通信息
    String MESSAGE_REQ_ONLINE_USER_NUM = "4";// 要求返回在线用户列表
    String MESSAGE_RES_ONLINE_USER_NUM = "5";// 返回在线用户列表
    String MESSAGE_REQ_LOGOUT = "6"; // 退出登录
    String MESSAGE_RES_LOGOUT_SUCCESS = "7"; // 成功退出登录
    String MESSAGE_RES_SEND_SUCCESS = "8"; // 消息发送成功
    String MESSAGE_RES_USER_NOT_EXIST = "9"; // 消息发送失败，私聊用户离线或不存在
    String MESSAGE_FILE_MSG = "10"; // 文件消息
    String MESSAGE_RECEIVE_FILE_SUCCESS = "11"; // 文件接收成功
    String MESSAGE_RECEIVE_FILE_FAIL = "12"; // 文件接收失败
    String MESSAGE_GET_OFFLINE_MSG = "13"; // 获取离线消息
}
