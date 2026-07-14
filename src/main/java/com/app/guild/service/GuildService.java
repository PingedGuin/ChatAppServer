package com.app.guild.service;

import com.app.guild.data.dto.guild.GuildConfigDto;
import com.app.guild.data.dto.guild.GuildDetailsDto;
import com.app.guild.data.entity.GuildEntity;
import com.app.guild.data.dto.guild.GuildInfoDto;
import com.app.guild.mapping.GuildMapper;
import com.app.guild.repository.GuildRepository;
import com.app.role.dto.RoleDto;
import com.app.member.dto.MemberPermissionDto;
import com.app.user.data.entity.UserEntity;
import com.app.user.service.UserService;
import com.github.benmanes.caffeine.cache.Cache;
import com.github.benmanes.caffeine.cache.Caffeine;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.concurrent.TimeUnit;

@Service
public class GuildService {
    private final GuildRepository guildRepository;
    private final ApplicationEventPublisher eventPublisher;
    private final Cache<Long, GuildEntity> guildEntityCache = Caffeine.newBuilder()
            .maximumSize(100_000)
            .expireAfterWrite(10, TimeUnit.MINUTES)
            .build();

    private final GuildMapper mapper;
    private final UserService userService;

//    private final Cache<Long, GuildInfoDto> guildCache = Caffeine.newBuilder()
//            .maximumSize(200_000)
//            .expireAfterWrite(10, TimeUnit.MINUTES)
//            .build();


    public GuildService(GuildRepository guildRepository, ApplicationEventPublisher eventPublisher, GuildMapper mapper, UserService userService) {
        this.guildRepository = guildRepository;
        this.eventPublisher = eventPublisher;
        this.mapper = mapper;
        this.userService = userService;
    }

    private GuildEntity getGuildEntity(Long guildId) {
        return guildEntityCache.get(guildId, id ->
                guildRepository.findById(id)
                        .orElseThrow(() -> new RuntimeException("Guild not found"))
        );
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

    public boolean isGuildOwner(Long guildId, Long userId) {
        return false;
    }

    public boolean isGuildMember(Long guildId, Long userId) {
        return false;
    }

    public boolean isGuildAdmin(Long guildId, Long userId) {
        return false;
    }

    public boolean kickMember(Long guildId, Long userId) {
        return false;
    }

    public boolean banMember(Long guildId, Long userId) {
        return false;
    }

    public boolean unbanMember(Long guildId, Long userId) {
        return false;
    }


    public GuildEntity getGuildEntityById(Long guildId) {
        if (guildId == null) throw new IllegalArgumentException("Guild Id cannot be null");

        var guild = guildEntityCache.getIfPresent(guildId);
        if (guild != null) return guild;

        var entity = guildRepository.findById(guildId).orElseThrow(()
                -> new RuntimeException("Guild not found"));

        guildEntityCache.put(guildId, entity);
        return entity;
    }

    public GuildInfoDto createGuild(GuildInfoDto guildInfo) {
        if (guildInfo == null) throw new IllegalArgumentException("Guild info cannot be null");
        UserEntity owner = userService.getUserEntityById(guildInfo.getOwnerId());

        GuildEntity entity = GuildEntity.builder()
                .name(guildInfo.getGuildName())
                .owner(owner).build();
        guildRepository.save(entity);

        return GuildInfoDto.builder()
                .id(entity.getId())
                .guildName(entity.getName())
                .ownerId(owner.getId())
                .build();
    }
}