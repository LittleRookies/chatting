package com.demo.chatting.chattingwebsocketA.interceptor;


import com.demo.chatting.chattingbean.bean.RedisMessage;
import com.demo.chatting.chattingbean.bean.StompPrincipal;
import com.demo.chatting.chattingredis.unit.RedisUnit;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.server.ServerHttpRequest;
import org.springframework.http.server.ServletServerHttpRequest;
import org.springframework.stereotype.Component;
import org.springframework.web.socket.WebSocketHandler;
import org.springframework.web.socket.server.support.DefaultHandshakeHandler;

import java.security.Principal;
import java.util.Map;
import java.util.UUID;

/**
 * @author Little Rookies
 * @create 2018-12-13 13:50
 */
@Component
public class CustomHandshakeHandler extends DefaultHandshakeHandler {
    private RedisUnit redisUnit;


    public CustomHandshakeHandler(RedisUnit redisUnit) {
        this.redisUnit = redisUnit;
    }

    // Custom class for storing principal
    @Override
    protected Principal determineUser(ServerHttpRequest request,
                                      WebSocketHandler wsHandler,
                                      Map<String, Object> attributes) {
        // Generate principal with UUID as name
//        可以自己定义
        String account = ((ServletServerHttpRequest) request).getServletRequest().getParameter("account");
        String s = UUID.randomUUID().toString();

        RedisMessage redisMessage = new RedisMessage();

        redisMessage.setSessionId(s);
        redisMessage.setType("A");
        redisUnit.save(account, redisMessage);
        return new StompPrincipal(s);
    }
}