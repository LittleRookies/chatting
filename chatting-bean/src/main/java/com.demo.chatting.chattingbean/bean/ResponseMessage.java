package com.demo.chatting.chattingbean.bean;

import java.io.Serializable;

/**
 * @author Little Rookies
 * @create 2018-12-12 15:27
 */
public class ResponseMessage implements Serializable {
    private String responseMessage;

    public String getResponseMessage() {
        return responseMessage;
    }

    public void setResponseMessage(String responseMessage) {
        this.responseMessage = responseMessage;
    }
}
