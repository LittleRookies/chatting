package com.demo.chatting.chattingwebsocketA.controller;

import com.alibaba.fastjson.JSONObject;
import com.demo.chatting.chattingbean.bean.ChatMessage;
import com.demo.chatting.chattingbean.bean.RequestMessage;
import com.demo.chatting.chattingbean.bean.ResponseMessage;
import com.demo.chatting.chattingwebsocketA.service.ChatService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.messaging.simp.annotation.SendToUser;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import java.security.Principal;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * @author Little Rookies
 * @create 2018-12-12 15:30
 */
@RestController
public class BroadcastCtl {
    private static Logger logger = LoggerFactory.getLogger(BroadcastCtl.class);

    private AtomicInteger count = new AtomicInteger(0);

    @Autowired
    private ChatService chatService;


    /**
     * @param requestMessage
     * @return
     * @MessageMapping 指定要接收消息的地址，类似@RequestMapping。除了注解到方法上，也可以注解到类上
     * @SendTo 会将接收到的消息发送到指定的路由目的地，所有订阅该消息的用户都能收到，属于广播。
     */
    @MessageMapping("/receive")
    @SendTo("/topic/getResponseAll")
    public ResponseMessage allbroadcast(RequestMessage requestMessage) {
        logger.info("receive message = {}", JSONObject.toJSONString(requestMessage));
        ResponseMessage responseMessage = new ResponseMessage();
        responseMessage.setResponseMessage("BroadcastCtl receive [" + count.incrementAndGet() + "] records");
        return responseMessage;
    }

    @RequestMapping(value = "/broadcast/index")
    public String broadcastIndex(HttpServletRequest req) {
        System.out.println(req.getRemoteHost());
        return "websocket/simple/ws-broadcast";
    }

    /**
     * @param requestMessage
     * @return
     * @MessageMapping 指定要接收消息的地址，类似@RequestMapping。除了注解到方法上，也可以注解到类上
     * @SendTo 消息目的地有UserDestinationMessageHandler来处理，会将消息路由到发送者对应的目的地,
     * 此外该注解还有个broadcast属性，表明是否广播。就是当有同一个用户登录多个session时，是否都能收到。取值true/false.
     */
    @MessageMapping("/receive-single")
    @SendToUser("/topic/getResponse")
    public ResponseMessage broadcast(RequestMessage requestMessage) {
        logger.info("receive message = {}", JSONObject.toJSONString(requestMessage));
        ResponseMessage responseMessage = new ResponseMessage();
        responseMessage.setResponseMessage("BroadcastCtl receive [" + count.incrementAndGet() + "] records");
        return responseMessage;
    }

    /**
     * @param chatMessage
     * @return
     * @MessageMapping 指定要接收消息的地址，类似@RequestMapping。除了注解到方法上，也可以注解到类上
     * @SendTo 消息目的地有UserDestinationMessageHandler来处理，会将消息路由到发送者对应的目的地,
     * 此外该注解还有个broadcast属性，表明是否广播。就是当有同一个用户登录多个session时，是否都能收到。取值true/false.
     */
    @MessageMapping("/receive-single-one")
    public void broadcastOne(ChatMessage chatMessage, Principal principal) {
        chatService.chat(chatMessage, principal);
    }


    @RequestMapping("/disconnect")
    public void disconnect(String account) {
        chatService.disconnect(account);
    }

}
