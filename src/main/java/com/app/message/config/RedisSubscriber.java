package com.app.message.config;

import org.springframework.stereotype.Service;

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
//            Message context = objectMapper.readValue(message, Message.class);
//            webSocketService.sendMessage(context);
//        } catch (Exception e) {
//            e.printStackTrace();
//        }
//    }
}