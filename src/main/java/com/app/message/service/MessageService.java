package com.app.message.service;

import com.app.message.data.dto.LoadMessagesRequest;
import com.app.message.data.entity.MessageEntity;
import com.app.message.repository.MessageRepository;
import com.app.policy.PolicyEngine;
import com.app.policy.data.PolicyContext;
import com.app.policy.policies.channel.context.SendMessageContent;
import com.github.benmanes.caffeine.cache.Cache;
import com.github.benmanes.caffeine.cache.Caffeine;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.Instant;
import java.util.List;
import java.util.concurrent.TimeUnit;

@Service
@Slf4j
public class MessageService {
    private final PolicyEngine policyEngine;
    private final WebSocketService webSocketService;
    private final MessageRepository messageRepository;
    private final Cache<String, Long> channelVersion = Caffeine.newBuilder()
            .maximumSize(100_00)
            .expireAfterWrite(10, TimeUnit.MINUTES)
            .build();
    private final Cache<String, List<PolicyContext>> msgCache = Caffeine.newBuilder()
            .maximumSize(100_000)
            .expireAfterWrite(10, TimeUnit.MINUTES)
            .build();

    public MessageService(PolicyEngine policyEngine, WebSocketService webSocketService, MessageRepository messageRepository) {
        this.policyEngine = policyEngine;
        this.webSocketService = webSocketService;
        this.messageRepository = messageRepository;
    }

    @Transactional
    public void handleSendMsgReq(SendMessageContent context) {
        policyEngine.check(context);

        MessageEntity messageEntity = toMessageEntity(context);
        try {
            MessageEntity savedMessage = messageRepository.save(messageEntity);

            PolicyContext dto = toDto(savedMessage);
            bumpVersion(context.getChannelId());

            webSocketService.sendMessage(dto);
        } catch (Exception e) {
            log.error("Failed to save message", e);
            throw new RuntimeException("Message send failed", e);
        }
    }

    public List<PolicyContext> getGeneralMessages(LoadMessagesRequest request) {
        long version = getVersion(request.getChannelId());

        String key = request.getChannelId() + ":" +
                version + ":" +
                request.getPageNumber() + ":" +
                request.getPageSize();

        List<PolicyContext> cached = msgCache.getIfPresent(key);

        if (cached != null) return cached;

        Pageable page = PageRequest.of(
                request.getPageNumber(),
                request.getPageSize()
        );

        List<MessageEntity> messages =
                messageRepository.getGeneralMessages(
                        request.getChannelId(),
                        page
                );

        List<PolicyContext> result =
                messages.stream()
                        .map(this::toDto)
                        .toList();

        msgCache.put(key, result);

        return result;
    }

    private MessageEntity toMessageEntity(SendMessageContent context) {
        MessageEntity entity = new MessageEntity();

        entity.setContent(context.getContent());
        entity.setGuildId(context.getGuildId());
        entity.setChannelId(context.getChannelId());
        entity.setSenderId(context.getSenderId());
        entity.setCreatedAt(Instant.now());
        return entity;
    }

    private PolicyContext toDto(MessageEntity entity) {
        SendMessageContent dto = new SendMessageContent();

        dto.setContent(entity.getContent());
        dto.setGuildId(entity.getGuildId());
        dto.setChannelId(entity.getChannelId());
        dto.setSenderId(entity.getSenderId());
        dto.setCreatedAt(entity.getCreatedAt());
        return dto;
    }

    private long getVersion(Long channelId) {
        Long version = channelVersion.getIfPresent(String.valueOf(channelId));
        return version != null ? version : 0L;
    }

    private void bumpVersion(Long channelId) {
        channelVersion.put(String.valueOf(channelId), getVersion(channelId) + 1);
    }

// 1. check if user banned
// 2. check membership (user in guild)
// 3. check channel access
// 4. check permissions (SEND_MESSAGE)
// 5. create message object
// 6. save message
// 7. publish event (WebSocket)

}
