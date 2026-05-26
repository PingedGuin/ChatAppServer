package com.app.guild.service;

import com.app.guild.data.Entity.GuildEntity;
import com.app.guild.data.dto.guild.GuildInfoDto;
import com.app.guild.mapping.GuildMapper;
import com.app.guild.repository.GuildRepository;
import com.app.role.dto.RoleDto;
import com.app.user.data.dto.MemberPermissionDto;
import com.github.benmanes.caffeine.cache.Cache;
import com.github.benmanes.caffeine.cache.Caffeine;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.concurrent.TimeUnit;

@Service
public class GuildService {
    private final GuildRepository guildRepository;
    private final GuildMapper mapper;

    private final Cache<Long, GuildInfoDto> guildCache = Caffeine.newBuilder()
            .maximumSize(200_000)
            .expireAfterWrite(10, TimeUnit.MINUTES)
            .build();


    public GuildService(GuildRepository guildRepository, GuildMapper mapper) {
        this.guildRepository = guildRepository;
        this.mapper = mapper;
    }

    public GuildInfoDto getGuild(Long guildId) {
        var dto = guildCache.getIfPresent(guildId);

        if (dto != null) return dto;

        GuildEntity guildEntity = guildRepository.findByGuildId(guildId).orElse(null);

        if (guildEntity == null) throw new RuntimeException("Guild not found");
        GuildInfoDto mapped = mapper.toDto(guildEntity);

        guildCache.put(guildId, mapped);
        return mapped;
    }

    public MemberPermissionDto getMemberPerms(Long guildId, Long userId) {
        return null;
    }

    public List<RoleDto> getAllRoles(Long guildId) {
        return getGuild(guildId).getRoles();
    }

    public Boolean isGuildOwner(Long guildId, Long userId) {
        return false;
    }

    public Boolean isGuildMember(Long guildId, Long userId) {
        return false;
    }

    public Boolean isGuildAdmin(Long guildId, Long userId) {
        return false;
    }

    public Boolean kickMember(Long guildId, Long userId) {
        return false;
    }

    public Boolean banMember(Long guildId, Long userId) {
        return false;
    }

    public Boolean unbanMember(Long guildId, Long userId) {
        return false;
    }


    public List<GuildInfoDto> getUserGuilds(Long userId) {
        return guildRepository.getUserGuilds(userId);
    }
}
