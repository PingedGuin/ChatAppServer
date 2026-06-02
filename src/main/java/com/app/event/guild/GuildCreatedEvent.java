package com.app.event.guild;

import lombok.Getter;

@Getter
public class GuildCreatedEvent {

    private final Long guildId;
    private final Long ownerId;

    public GuildCreatedEvent(Long guildId, Long ownerId) {
        this.guildId = guildId;
        this.ownerId = ownerId;
    }
}