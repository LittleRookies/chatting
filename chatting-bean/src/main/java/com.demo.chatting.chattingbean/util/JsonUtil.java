package com.demo.chatting.chattingbean.util;

import com.fasterxml.jackson.databind.ObjectMapper;

/**
 * @Author: Little Rookies
 * @Date: 2018/11/9 17:04
 */
public class JsonUtil {

    private static ObjectMapper objectMapper = new ObjectMapper();

    public static ObjectMapper getMapper() {
        return objectMapper;
    }


}
