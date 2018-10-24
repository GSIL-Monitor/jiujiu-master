package com.jiu.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

/**
 * Created by lukai on 2018/8/2.
 */
@RestController
@RequestMapping("/test")
public class TestController {

    @Value("${my.message}")
    private String message;

    @Autowired
    private RedisTemplate redisTemplate;

    @Autowired
    private StringRedisTemplate stringRedisTemplate;

    @RequestMapping(value = "/getMessage",method = {RequestMethod.POST})
    public String getMessage(){
        redisTemplate.opsForValue().set("test",message);
        return (String) redisTemplate.opsForValue().get("test");
    }
}
