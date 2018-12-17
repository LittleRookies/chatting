package com.demo.chatting.chattingbean.bean;



import java.io.Serializable;

/**
 * @author Jiang Hao
 * @create 2018-12-17 13:27
 */
public class SendMqMessage implements Serializable {
    private String account;
    private String message;
    private String sender;

    public String getSender() {
        return sender;
    }

    public void setSender(String sender) {
        this.sender = sender;
    }

    public String getAccount() {
        return account;
    }

    public void setAccount(String account) {
        this.account = account;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
