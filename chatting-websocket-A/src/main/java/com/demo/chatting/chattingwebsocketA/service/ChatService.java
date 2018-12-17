package com.demo.chatting.chattingwebsocketA.service;

import com.alibaba.fastjson.JSON;
import com.demo.chatting.chattingbean.bean.ChatMessage;
import com.demo.chatting.chattingbean.bean.ReceiveMqMessage;
import com.demo.chatting.chattingbean.bean.ResponseMessage;
import com.demo.chatting.chattingbean.bean.SendMqMessage;
import com.demo.chatting.chattingbean.util.JsonUtil;
import com.demo.chatting.chattingredis.unit.RedisUnit;
import com.demo.chatting.chattingwebsocketA.util.MqInfoUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.core.Message;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.security.Principal;

/**
 * @author Little Rookies
 * @create 2018-12-13 15:38
 */
@Service
public class ChatService {
    @Autowired
    private RabbitTemplate rabbitTemplate;

    @Autowired
    private SimpMessagingTemplate simpMessagingTemplate;

    @Autowired
    private RedisUnit redisUnit;

    @Autowired
    private MqInfoUtil mqInfoUtil;

    private final Logger logger = LoggerFactory.getLogger(ChatService.class);

    @RabbitListener(queues = "receiveQueueA")
    public void receive(Message message) throws IOException {
        ReceiveMqMessage receiveMqMessage = JsonUtil.getMapper().readValue(message.getBody(), ReceiveMqMessage.class);

        ResponseMessage responseMessage = new ResponseMessage();
        responseMessage.setResponseMessage(receiveMqMessage.getSender() + ":" + receiveMqMessage.getMessage());
        simpMessagingTemplate.convertAndSendToUser(receiveMqMessage.getSessionid(), "/topic/getResponse/one", JSON.toJSON(responseMessage));

    }


    public void chat(ChatMessage chatMessage, Principal principal) {
        logger.info("这是{}发送的消息：{}", principal.getName(), JSON.toJSON(chatMessage));

        String name = chatMessage.getName();
        String message = chatMessage.getMessage();
        String account = chatMessage.getAccount();

//        返回的消息
        ResponseMessage responseMessage = new ResponseMessage();
        responseMessage.setResponseMessage("我：" + message + "\n");


        SendMqMessage sendMqMessage = new SendMqMessage();
//        向mq发送消息
        sendMqMessage.setAccount(name);
        sendMqMessage.setMessage(message);
        sendMqMessage.setSender(account);
        rabbitTemplate.convertAndSend(mqInfoUtil.getExchange(), mqInfoUtil.getBind(), sendMqMessage);

//        返回消息
        simpMessagingTemplate.convertAndSendToUser(principal.getName(), "/topic/getResponse/one", JSON.toJSON(responseMessage));
    }

    public void disconnect(String account) {
        redisUnit.del(account);
    }
}
