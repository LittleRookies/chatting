package com.demo.chatting.chattingbean.bean;

import java.io.Serializable;

/**
 * @author Little Rookies
 * @create 2018-12-12 15:27
 */
public class RequestMessage implements Serializable {
    private String name;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
