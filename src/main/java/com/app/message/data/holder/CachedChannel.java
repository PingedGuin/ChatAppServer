package com.app.message.data.holder;

import com.app.policy.policies.channel.context.Message;

import java.util.List;
import java.util.concurrent.ConcurrentLinkedQueue;

public record CachedChannel(Long channelId, ConcurrentLinkedQueue<Message> messages) {
    private static final int MAX_MESSAGES = 100;

    public CachedChannel(Long channelId) {
        this(channelId, new ConcurrentLinkedQueue<>());
    }

    public void insertMessage(Message message) {
        if (messages.size() >= MAX_MESSAGES) {
            messages.poll();
        }

        messages.add(message);
    }

    public List<Message> getMessages() {
        return List.copyOf(messages);
    }
}
