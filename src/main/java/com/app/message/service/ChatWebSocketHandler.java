package com.app.message.service;

import com.app.policy.data.PolicyContext;
import com.app.policy.policies.channel.context.Message;
import com.app.websocket.Session;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.extern.slf4j.Slf4j;
import org.jspecify.annotations.NonNull;
import org.springframework.stereotype.Component;
import org.springframework.web.socket.CloseStatus;
import org.springframework.web.socket.TextMessage;
import org.springframework.web.socket.WebSocketSession;
import org.springframework.web.socket.handler.TextWebSocketHandler;

@Slf4j
@Component
public class ChatWebSocketHandler extends TextWebSocketHandler {

    private final WebSocketService webSocketService;
    private final ObjectMapper objectMapper;
    private final MessageService messageService;

    public ChatWebSocketHandler(WebSocketService webSocketService,
                                ObjectMapper objectMapper, MessageService messageService) {
        this.webSocketService = webSocketService;
        this.objectMapper = objectMapper;
        this.messageService = messageService;
    }

    @Override
    public void afterConnectionEstablished(@NonNull WebSocketSession session) {
        String userId = getUserId(session);

        webSocketService.addSession(Long.parseLong(userId), new Session(session));
    }

    @Override
    protected void handleTextMessage(@NonNull WebSocketSession session, TextMessage message) {
        String payload = message.getPayload();
        Message dto;
        try {
            dto = objectMapper.readValue(payload, Message.class);
        } catch (Exception e) {
            log.error("Error parsing message: {}", payload, e);
            return;
        }
        messageService.handleSendMsgReq(dto);
    }

    @Override
    public void afterConnectionClosed(@NonNull WebSocketSession session, @NonNull CloseStatus status) {
        String userId = getUserId(session);

        webSocketService.removeSession(Long.parseLong(userId));
    }

    private String getUserId(WebSocketSession session) {
        return session.getId();
    }
}