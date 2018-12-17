package com.demo.chatting.chattingwebsocketB;

import com.demo.chatting.chattingbean.bean.SendMqMessage;
import com.demo.chatting.chattingwebsocketB.util.MqInfoUtil;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest
public class ChattingWebsocketApplicationTests {
    @Autowired
    private RabbitTemplate rabbitTemplate;

    @Test
    public void contextLoads() {
        SendMqMessage sendMqMessage = new SendMqMessage();
        sendMqMessage.setSender("2133");
//        rabbitTemplate.convertAndSend(, , sendMqMessage);
    }

}

