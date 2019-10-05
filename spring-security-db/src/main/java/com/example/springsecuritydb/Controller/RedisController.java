package com.example.springsecuritydb.Controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.data.redis.core.ValueOperations;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class RedisController {

    @Autowired
    StringRedisTemplate redisTemplate;

    @GetMapping("/set")
    public String setValue(){
        String a="abc";
        ValueOperations<String,String> ops=redisTemplate.opsForValue();
        ops.set(a,"abc");
        return a;
    }
}
