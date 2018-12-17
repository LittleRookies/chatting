package com.demo.chatting.chattingwebsocketB.interceptor;

import org.springframework.http.server.ServerHttpRequest;
import org.springframework.http.server.ServerHttpResponse;
import org.springframework.stereotype.Component;
import org.springframework.web.socket.WebSocketHandler;
import org.springframework.web.socket.server.HandshakeInterceptor;

import java.io.InputStream;
import java.util.Map;

/**
 * 拦截websocket的握手请求。在服务端和客户端在进行握手时会被执行
 *
 * @author Little Rookies
 * @create 2018-12-12 16:08
 */
@Component
public class MyHandShakeInterceptor implements HandshakeInterceptor {
    @Override
    public boolean beforeHandshake(ServerHttpRequest request, ServerHttpResponse response, WebSocketHandler wsHandler, Map<String, Object> attributes) throws Exception {

        System.out.println(this.getClass().getCanonicalName() + "http协议转换websoket协议进行前, 握手前" + request.getURI());
        // http协议转换websoket协议进行前，可以在这里通过session信息判断用户登录是否合法
        InputStream body = request.getBody();
        return true;
    }

    @Override
    public void afterHandshake(ServerHttpRequest request, ServerHttpResponse response, WebSocketHandler wsHandler, Exception exception) {
        //握手成功后,
        System.out.println(this.getClass().getCanonicalName() + "握手成功后...");
    }
}
