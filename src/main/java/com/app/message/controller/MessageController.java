package com.app.message.controller;

import com.app.message.data.dto.LoadMessagesRequest;
import com.app.message.service.MessageService;
import com.app.policy.PolicyEngine;
import com.app.policy.policies.channel.join.context.JoinChannelContext;
import com.app.policy.policies.channel.send.Message;
import com.app.register.dtos.socket.SocketMessage;
import lombok.extern.slf4j.Slf4j;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

import java.security.Principal;
import java.util.List;

@Slf4j
@Controller
public class MessageController {
    private final MessageService messageService;
    private final PolicyEngine policyEngine;

    public MessageController(MessageService messageService, PolicyEngine policyEngine) {
        this.messageService = messageService;
        this.policyEngine = policyEngine;
    }

    @MessageMapping("messages/sendMessage")
    public void send(@Payload Message content) {
        if (content == null) {
            throw new RuntimeException("Invalid message");
        }

        System.out.println("RECEIVED MESSAGE: " + content.getContent());

        content.setChannelId(1L);
        content.setGuildId(1221L);
        content.setSenderId(3134132L);
        messageService.handleSendMsgReq(content);
    }

    @MessageMapping("/socket")
    public void socket(@Payload SocketMessage message) {
        log.info("socket message: {}", message);
    }

    @GetMapping("/messages/general")
    public List<Message> getMessages(@Payload LoadMessagesRequest messagesReq) {

        return messageService.getChannelMessages(messagesReq);
    }

    @MessageMapping("/channel/join")
    public void joinChannel(JoinChannelContext context, Principal user) {
        policyEngine.check(context);

    }
}