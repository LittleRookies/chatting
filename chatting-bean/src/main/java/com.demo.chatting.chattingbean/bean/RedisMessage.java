package com.demo.chatting.chattingbean.bean;

import java.io.Serializable;

/**
 * @author Jiang Hao
 * @create 2018-12-17 11:31
 */
public class RedisMessage implements Serializable {
    private String type;
    private String sessionId;

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getSessionId() {
        return sessionId;
    }

    public void setSessionId(String sessionId) {
        this.sessionId = sessionId;
    }
}
