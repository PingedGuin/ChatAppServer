package com.app.guild.data.mapper;

import com.app.guild.data.dto.guild.GuildInfoDto;
import com.app.guild.data.entity.GuildEntity;
import org.springframework.stereotype.Component;

@Component
public class GuildMapper {

    public GuildInfoDto toDto(GuildEntity entity) {
        return GuildInfoDto.builder()
                .id(entity.getId())
                .guildName(entity.getName())
                .guildBanner(entity.getGuildBanner())
                .guildIcon(entity.getGuildIcon())
                .memberCount(entity.getMemberCount())
                .channelCount(entity.getChannelCount())
                .ownerId(entity.getOwner().getId())
                .description(entity.getDescription())
                .build();
    }
}
