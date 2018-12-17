package com.demo.chatting.chattingbean.bean;

import java.io.Serializable;

/**
 * @author Little Rookies
 * @create 2018-12-13 15:41
 */
public class ChatMessage implements Serializable {
    //    接收者
    private String name;
    //    消息
    private String message;
    //    发送者
    private String account;

    public String getAccount() {
        return account;
    }

    public void setAccount(String account) {
        this.account = account;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
