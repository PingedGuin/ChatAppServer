package com.app.message.controller;

import com.app.message.data.dto.chat.command.LeaveRequest;
import com.app.message.data.dto.chat.command.TypingRequest;
import com.app.message.service.WebSocketService;
import com.app.policy.PolicyEngine;
import com.app.policy.policies.channel.join.context.JoinChannelContext;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.stereotype.Controller;

@Controller
public class ChatController {
    private final PolicyEngine policy;
    private final WebSocketService webSocketService;

    public ChatController(PolicyEngine policy, WebSocketService webSocketService) {
        this.policy = policy;
        this.webSocketService = webSocketService;
    }

    @MessageMapping("/channel.join")
    public void join(@Payload JoinChannelContext req) {
        // if (policy.check(req)){
        // return;
        // }

        webSocketService.joinChannel(req.getChannelId(), req.getGuildId(), req.getUserId());
    }

    @MessageMapping("/channel.leave")
    public void leave(@Payload LeaveRequest req) {
    }

    @MessageMapping("/channel.typing")
    public void typing(@Payload TypingRequest req) {
    }
}
