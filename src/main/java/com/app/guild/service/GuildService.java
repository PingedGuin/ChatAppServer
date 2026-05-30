package com.app.guild.service;

import com.app.guild.data.dto.guild.GuildConfigDto;
import com.app.guild.data.dto.guild.GuildDetailsDto;
import com.app.guild.data.entity.GuildEntity;
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
    private final Cache<Long, GuildEntity> guildEntityCache = Caffeine.newBuilder()
            .maximumSize(100_000)
            .expireAfterWrite(10, TimeUnit.MINUTES)
            .build();

    private final GuildMapper mapper;

//    private final Cache<Long, GuildInfoDto> guildCache = Caffeine.newBuilder()
//            .maximumSize(200_000)
//            .expireAfterWrite(10, TimeUnit.MINUTES)
//            .build();


    public GuildService(GuildRepository guildRepository, GuildMapper mapper) {
        this.guildRepository = guildRepository;
        this.mapper = mapper;
    }

    private GuildEntity getGuildEntity(Long guildId) {
        var entity = guildEntityCache.getIfPresent(guildId);
        if (entity != null) return entity;
        var guildEntityOptional = guildRepository.findById(guildId).orElseThrow(() -> new RuntimeException("Guild not found"));
        guildEntityCache.put(guildId, guildEntityOptional);
        return guildEntityOptional;

    }

    public GuildInfoDto getGuildInfo(Long guildId) {
        return mapper.toDto(getGuildEntity(guildId));
    }

    public GuildDetailsDto getGuildDetails(Long guildId) {
        return mapper.toDetailsDto(getGuildEntity(guildId));
    }

    public GuildConfigDto getGuildConfig(Long guildId) {
        return mapper.toConfigDto(getGuildEntity(guildId));
    }

    public MemberPermissionDto getMemberPerms(Long guildId, Long userId) {
        return null;
    }

    public List<RoleDto> getAllRoles(Long guildId) {
        return getGuildDetails(guildId).getRoles();
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
