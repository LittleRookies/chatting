package com.demo.chatting.chattingcenter;

import org.springframework.amqp.rabbit.annotation.EnableRabbit;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

/**
 * @author Jiang Hao
 * @create 2018-12-17 13:48
 */
@EnableRabbit //开启rabbitmq监听
@ComponentScan(basePackages = {"com.demo.chatting.chattingredis", "com.demo.chatting.chattingcenter"})
@SpringBootApplication
public class ChattingCenterApplication {
    public static void main(String[] args) {
        SpringApplication.run(ChattingCenterApplication.class, args);
    }

}
