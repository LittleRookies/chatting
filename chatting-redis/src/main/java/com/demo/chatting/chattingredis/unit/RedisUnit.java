package com.demo.chatting.chattingredis.unit;

import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;

/**
 * @author Little Rookies
 * @create 2018-12-17 10:10
 */
@Component
public class RedisUnit {
    @Resource
    private RedisTemplate<String, Object> redisTemplate;

    public void save(String key, Object value) {
        redisTemplate.opsForValue().set(key, value);
    }

    public Object get(String key) {
        return redisTemplate.opsForValue().get(key);
    }

    public void del(String key) {
        redisTemplate.delete(key);
    }
}
