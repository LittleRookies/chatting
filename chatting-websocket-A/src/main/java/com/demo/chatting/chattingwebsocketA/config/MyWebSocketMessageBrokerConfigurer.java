package com.demo.chatting.chattingwebsocketA.config;


import com.demo.chatting.chattingredis.unit.RedisUnit;
import com.demo.chatting.chattingwebsocketA.interceptor.CustomHandshakeHandler;
import com.demo.chatting.chattingwebsocketA.interceptor.MyChannelInterceptorAdapter;
import com.demo.chatting.chattingwebsocketA.interceptor.MyHandShakeInterceptor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.messaging.simp.config.ChannelRegistration;
import org.springframework.messaging.simp.config.MessageBrokerRegistry;
import org.springframework.web.socket.config.annotation.EnableWebSocketMessageBroker;
import org.springframework.web.socket.config.annotation.StompEndpointRegistry;
import org.springframework.web.socket.config.annotation.WebSocketMessageBrokerConfigurer;

/**
 * @author Little Rookies
 * @create 2018-12-12 15:45
 */
@Configuration
// 此注解表示使用STOMP协议来传输基于消息代理的消息，此时可以在@Controller类中使用@MessageMapping
@EnableWebSocketMessageBroker
public class MyWebSocketMessageBrokerConfigurer implements WebSocketMessageBrokerConfigurer {

    @Autowired
    private MyHandShakeInterceptor myHandShakeInterceptor;

    @Autowired
    private RedisUnit redisUnit;

    /**
     * 注册 Stomp的端点
     * <p>
     * addEndpoint：添加STOMP协议的端点。这个HTTP URL是供WebSocket或SockJS客户端访问的地址
     * withSockJS：指定端点使用SockJS协议
     */
    @Override
    public void registerStompEndpoints(StompEndpointRegistry registry) {
        registry.addEndpoint("/websocket-simple")
                .setAllowedOrigins("*") // 添加允许跨域访问
                //. setAllowedOrigins("http://mydomain.com");
                .addInterceptors(myHandShakeInterceptor) // 添加自定义拦截
                .setHandshakeHandler(new CustomHandshakeHandler(redisUnit))
                .withSockJS();
    }

    /**
     * 配置消息代理
     * 启动简单Broker，消息的发送的地址符合配置的前缀来的消息才发送到这个broker
     */
    @Override
    public void configureMessageBroker(MessageBrokerRegistry registry) {
        registry.enableSimpleBroker("/topic", "/queue");

    }

    @Override
    public void configureClientInboundChannel(ChannelRegistration registration) {
//        .interceptors(myChannelInterceptorAdapter)
        ChannelRegistration interceptors = registration.interceptors(new MyChannelInterceptorAdapter());
        WebSocketMessageBrokerConfigurer.super.configureClientInboundChannel(interceptors);
    }
}
