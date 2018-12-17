package com.demo.chatting.chattingwebsocketB.config;

import com.demo.chatting.chattingwebsocketB.util.MqInfoUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.core.*;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.amqp.support.converter.Jackson2JsonMessageConverter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @Author: Little Rookies
 * @Date: 2018/11/9 14:56
 */
@Configuration
public class MyRabbitmqConfig {
    @Autowired
    private RabbitTemplate rabbitTemplate;

    @Autowired
    private MqInfoUtil mqInfoUtil;


    private Logger log = LoggerFactory.getLogger(MyRabbitmqConfig.class);

    @Bean
    public AmqpTemplate amqpTemplate() {
        // 使用jackson 消息转换器
        rabbitTemplate.setMessageConverter(new Jackson2JsonMessageConverter());
        rabbitTemplate.setEncoding("UTF-8");
        // 消息发送失败返回到队列中，yml需要配置 publisher-returns: true
        rabbitTemplate.setMandatory(true);
        rabbitTemplate.setReturnCallback((message, replyCode, replyText, exchange, routingKey) -> {
            String correlationId = message.getMessageProperties().getCorrelationId();
            log.debug("消息：{} 发送失败, 应答码：{} 原因：{} 交换机: {}  路由键: {}", correlationId, replyCode, replyText, exchange, routingKey);
        });
        // 消息确认，yml需要配置 publisher-confirms: true
        rabbitTemplate.setConfirmCallback((correlationData, ack, cause) -> {
            if (ack) {
                try {
                    try {
                        log.debug("消息发送到exchange成功,id: {}", correlationData.getId());

                    } catch (Exception ignored) {

                    }
                } catch (Exception ignored) {
                }
            } else {
                log.debug("消息发送到exchange失败,原因: {}", cause);
            }
        });
        return rabbitTemplate;
    }

    //-----------------------------------------------------声明Direct交换机------------------------------------------------------
    @Bean
    public Exchange directExchange() {
        return ExchangeBuilder.directExchange(mqInfoUtil.getExchange()).durable(true).build();
    }

    @Bean
    public Queue receiveQueue() {
        return QueueBuilder.durable(mqInfoUtil.getRecQueue()).build();
    }

    @Bean
    public Queue sendQueue() {
        return QueueBuilder.durable(mqInfoUtil.getSnedQueue()).build();
    }

    @Bean
    public Binding directBindSend(
            @Qualifier("directExchange") Exchange exchange,
            @Qualifier("sendQueue") Queue queue
    ) {
        return BindingBuilder.bind(queue).to(exchange).with(mqInfoUtil.getBind()).noargs();
    }
}
