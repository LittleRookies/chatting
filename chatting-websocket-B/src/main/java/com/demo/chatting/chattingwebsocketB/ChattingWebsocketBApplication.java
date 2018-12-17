package com.demo.chatting.chattingwebsocketB;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

@ComponentScan(basePackages = {"com.demo.chatting.chattingredis", "com.demo.chatting.chattingwebsocketB"})
@SpringBootApplication
public class ChattingWebsocketBApplication {

    public static void main(String[] args) {
        SpringApplication.run(ChattingWebsocketBApplication.class, args);
    }

}

