package com.demo.demo.serviceImpl.redis;

import com.demo.demo.collections.StudentCollections;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Component;

import java.time.Duration;

@Component
public class RedisService {
    @Autowired
    private RedisTemplate<String,Object> redisTemplate;

    @Autowired
    private ObjectMapper objectMapper;

    public <T> T get(String key, Class<T> studentCollectionsClass) throws JsonProcessingException {
        Object o = redisTemplate.opsForValue().get(key);
        if (o != null) {
            return objectMapper.readValue(o.toString(), studentCollectionsClass);
        } else
            return null;
    }

    public void set(String key, String value) {
        redisTemplate.opsForValue().set(key, value, Duration.ofHours(1));
    }
}
