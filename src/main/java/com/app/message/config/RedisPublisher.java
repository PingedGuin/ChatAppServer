package com.app.message.config;

import com.app.policy.data.PolicyContext;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Service;
import com.fasterxml.jackson.databind.ObjectMapper;
@Deprecated
@Service
public class RedisPublisher {

    private final StringRedisTemplate redisTemplate;
    private final ObjectMapper objectMapper = new ObjectMapper();

    public RedisPublisher(StringRedisTemplate redisTemplate) {
        this.redisTemplate = redisTemplate;
    }

    public void publish(PolicyContext dto) {
        try {
            String json = objectMapper.writeValueAsString(dto);
            redisTemplate.convertAndSend("chat", json);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}