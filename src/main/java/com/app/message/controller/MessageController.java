package com.app.message.controller;

import com.app.message.data.dto.ChatMessageDto;
import com.app.message.data.dto.LoadMessagesRequest;
import com.app.message.service.MessageService;
import com.app.policy.PolicyEngine;
import com.app.register.dtos.socket.SocketMessage;
import lombok.extern.slf4j.Slf4j;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.List;

@Slf4j
@Controller
public class MessageController {
    private final MessageService messageService;

    public MessageController(MessageService messageService) {
        this.messageService = messageService;
    }

    @MessageMapping("messages/sendMessage")
    public void send(@Payload ChatMessageDto chatMessageDto) {
        if (chatMessageDto == null) {
            throw new RuntimeException("Invalid message");
        }
        chatMessageDto.setChannelId(1221L);
        chatMessageDto.setGuildId(1221L);
        chatMessageDto.setSenderId(3134132L);
        messageService.handleSendMsgReq(chatMessageDto);
    }
    @MessageMapping("/socket")
    public void socket(@Payload SocketMessage message) {
        log.info("socket message: {}", message);
    }
    @GetMapping("/messages/general")
    public List<ChatMessageDto> getMessages(@Payload LoadMessagesRequest loadMessagesRequest) {

        return messageService.getGeneralMessages(loadMessagesRequest);
    }
}