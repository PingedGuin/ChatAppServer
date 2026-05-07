package com.app.message.data.holder;

import com.app.policy.policies.channel.context.Message;

import java.util.concurrent.ConcurrentLinkedQueue;

public record CachedChannel(Long channelId, ConcurrentLinkedQueue<Message> messages) {
    public CachedChannel(Long channelId) {
        this(channelId, new ConcurrentLinkedQueue<>());
    }

    public void insertMessage(Message message) {
        messages.add(message);
    }

    public ConcurrentLinkedQueue<Message> getMessages() {
        return messages; //todo make it return last 100 messages
    }
}
