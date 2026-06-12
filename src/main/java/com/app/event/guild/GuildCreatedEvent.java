package com.app.event.guild;

import lombok.AllArgsConstructor;
import lombok.Getter;
@AllArgsConstructor
@Getter
public class GuildCreatedEvent {
    private final Long guildId;
    private final Long userId;
}