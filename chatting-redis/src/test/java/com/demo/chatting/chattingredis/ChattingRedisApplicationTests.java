package com.demo.chatting.chattingredis;

import com.demo.chatting.chattingredis.unit.RedisUnit;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

/**
 * @author Little Rookies
 * @create 2018-12-17 10:51
 */
@RunWith(SpringRunner.class)
@SpringBootTest
public class ChattingRedisApplicationTests {
    @Autowired
    RedisUnit redisUnit;

    @Test
    public void contextLoads() {
        redisUnit.save("123", "123");
        System.out.println(redisUnit.get("123"));
    }
}
