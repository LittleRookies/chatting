package com.demo.chatting.chattingbean.bean;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import java.io.Serializable;

/**
 * @author Jiang Hao
 * @create 2018-12-17 13:37
 */
@JsonIgnoreProperties
public class ReceiveMqMessage implements Serializable {
    private String sessionid;
    private String message;
    private String sender;

    public String getSessionid() {
        return sessionid;
    }

    public void setSessionid(String sessionId) {
        this.sessionid = sessionId;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public String getSender() {
        return sender;
    }

    public void setSender(String sender) {
        this.sender = sender;
    }
}
