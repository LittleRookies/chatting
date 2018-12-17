package com.demo.chatting.chattingcenter.mq;


import com.demo.chatting.chattingbean.bean.ReceiveMqMessage;
import com.demo.chatting.chattingbean.bean.RedisMessage;
import com.demo.chatting.chattingbean.bean.SendMqMessage;
import com.demo.chatting.chattingbean.enem.TypeEnum;
import com.demo.chatting.chattingbean.util.JsonUtil;
import com.demo.chatting.chattingredis.unit.RedisUnit;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.IOException;

/**
 * @author Jiang Hao
 * @create 2018-12-17 14:02
 */
@Service
public class Message {
    @Autowired
    private RedisUnit redisUnit;

    @Autowired
    private RabbitTemplate rabbitTemplate;


    @RabbitListener(queues = {"sendQueueA", "sendQueueB"})
    public void receive(org.springframework.amqp.core.Message message) throws IOException {
        SendMqMessage sendMqMessage = JsonUtil.getMapper().readValue(message.getBody(), SendMqMessage.class);

        String account = sendMqMessage.getAccount();
//        获取redis数据
        RedisMessage redisMessage = (RedisMessage) redisUnit.get(account);
//        获取接收者mq的信息
        TypeEnum byType = TypeEnum.getByType(redisMessage.getType());
//        构造返回数据
        ReceiveMqMessage receiveMqMessage = new ReceiveMqMessage();
        receiveMqMessage.setMessage(sendMqMessage.getMessage());
        receiveMqMessage.setSender(sendMqMessage.getSender());
        receiveMqMessage.setSessionid(redisMessage.getSessionId());
        assert byType != null;
        rabbitTemplate.convertAndSend(byType.getExchange(), byType.getBind(), receiveMqMessage);

    }
}
