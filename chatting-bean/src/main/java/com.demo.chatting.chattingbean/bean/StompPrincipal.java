package com.demo.chatting.chattingbean.bean;

import java.security.Principal;

/**
 * @author Little Rookies
 * @create 2018-12-13 13:46
 */
public class StompPrincipal implements Principal {
    private String id;

    public StompPrincipal(String id) {
        this.id = id;
    }

    @Override
    public String getName() {
        return id;
    }
}