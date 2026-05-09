package com.app.message.service;

import com.app.policy.policies.channel.send.Message;
import org.springframework.stereotype.Service;
import lombok.RequiredArgsConstructor;
import org.springframework.messaging.simp.SimpMessagingTemplate;

@Service
@RequiredArgsConstructor
public class WebSocketService {

    private final SimpMessagingTemplate messagingTemplate;

    public void sendMessage(Message messageDto) {

        Long channelId = messageDto.getChannelId();

        messagingTemplate.convertAndSend(
                "/topic/channel/" + channelId,
                messageDto
        );
    }
}