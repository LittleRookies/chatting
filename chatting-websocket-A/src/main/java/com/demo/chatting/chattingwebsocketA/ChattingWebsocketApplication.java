package com.demo.chatting.chattingwebsocketA;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

@ComponentScan(basePackages = {"com.demo.chatting.chattingredis", "com.demo.chatting.chattingwebsocketA"})
@SpringBootApplication
public class ChattingWebsocketApplication {

    public static void main(String[] args) {
        SpringApplication.run(ChattingWebsocketApplication.class, args);
    }

}

