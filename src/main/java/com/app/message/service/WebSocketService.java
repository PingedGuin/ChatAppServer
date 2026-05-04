package com.app.message.service;

import com.app.message.data.dto.ChatMessageDto;
import com.app.websocket.Session;
import lombok.Getter;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.Map;
import java.util.Set;
import java.util.concurrent.ConcurrentHashMap;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.web.socket.TextMessage;

@Slf4j
@Service
@Getter
public class WebSocketService {
    private final Map<String, Session> sessions = new ConcurrentHashMap<>();
    private final Map<Long, Set<Long>> channelUsers = new ConcurrentHashMap<>();
    private final ObjectMapper objectMapper = new ObjectMapper();

    public void addSession(String userId, Session session) {
        sessions.put(userId, session);
    }

    public void removeSession(String userId) {
        sessions.remove(userId);
    }

    public void sendMessage(ChatMessageDto messageDto) {
        Long channelId = messageDto.getChannelId();
        Set<Long> users = channelUsers.get(channelId);

        if (users == null) return;

        try {
            String json = objectMapper.writeValueAsString(messageDto);

            for (Long userId : users) {
                Session sessionWrapper = sessions.get(userId);

                if (sessionWrapper != null) {
                    sessionWrapper.getSession().sendMessage(new TextMessage(json));
                }
            }

        } catch (Exception e) {
            log.error("Failed to send message",e);
        }
    }

    public void joinChannel(Long channelId, Long memberId) {
        channelUsers
                .computeIfAbsent(channelId, k -> ConcurrentHashMap.newKeySet())
                .add(memberId);
    }

    public void leaveChannel(Long channelId, Long userId) {
        Set<Long> users = channelUsers.get(channelId);
        if (users != null) {
            users.remove(userId);
        }
    }
}
