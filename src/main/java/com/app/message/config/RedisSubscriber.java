package com.app.message.config;

import com.app.message.service.WebSocketService;
import com.app.policy.data.PolicyContext;
import com.app.policy.policies.channel.context.Message;
import org.springframework.stereotype.Service;
import com.fasterxml.jackson.databind.ObjectMapper;
@Deprecated
@Service
public class RedisSubscriber {
//
//    private final WebSocketService webSocketService;
//    private final ObjectMapper objectMapper;
//
//    public RedisSubscriber(WebSocketService webSocketService, ObjectMapper objectMapper) {
//        this.webSocketService = webSocketService;
//        this.objectMapper = objectMapper;
//    }
//    public void onMessage(String message) {
//        try {
//            Message dto = objectMapper.readValue(message, Message.class);
//            webSocketService.sendMessage(dto);
//        } catch (Exception e) {
//            e.printStackTrace();
//        }
//    }
}