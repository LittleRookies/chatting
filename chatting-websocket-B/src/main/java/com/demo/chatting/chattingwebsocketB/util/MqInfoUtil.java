package com.demo.chatting.chattingwebsocketB.util;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

/**
 * @author Little Rookies
 * @create 2018-12-17 17:25
 */
@Component
public class MqInfoUtil {
    @Value("${exchange}")
    private String exchange;

    @Value("${bind}")
    private String bind;

    @Value("${recQueue}")
    private String recQueue;

    @Value("${sendQueue}")
    private String snedQueue;

    public String getExchange() {
        return exchange;
    }

    public void setExchange(String exchange) {
        this.exchange = exchange;
    }

    public String getBind() {
        return bind;
    }

    public void setBind(String bind) {
        this.bind = bind;
    }

    public String getRecQueue() {
        return recQueue;
    }

    public void setRecQueue(String recQueue) {
        this.recQueue = recQueue;
    }

    public String getSnedQueue() {
        return snedQueue;
    }

    public void setSnedQueue(String snedQueue) {
        this.snedQueue = snedQueue;
    }
}
