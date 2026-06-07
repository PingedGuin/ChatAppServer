package com.app.guild.service;

import com.app.event.guild.GuildCreatedEvent;
import com.app.guild.data.dto.guild.GuildConfigDto;
import com.app.guild.data.dto.guild.GuildCreate;
import com.app.guild.data.dto.guild.GuildDetailsDto;
import com.app.guild.data.entity.GuildEntity;
import com.app.guild.data.dto.guild.GuildInfoDto;
import com.app.guild.mapping.GuildMapper;
import com.app.guild.repository.GuildRepository;
import com.app.role.dto.RoleDto;
import com.app.member.dto.MemberPermissionDto;
import com.app.user.data.dto.UserDto;
import com.app.user.service.UserService;
import com.github.benmanes.caffeine.cache.Cache;
import com.github.benmanes.caffeine.cache.Caffeine;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.transaction.support.TransactionSynchronization;
import org.springframework.transaction.support.TransactionSynchronizationManager;

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

    @Transactional
    public GuildInfoDto createGuild(GuildCreate guildCreate) {
        UserDto userDto = userService.getUserById(guildCreate.getOwnerId());
        var userEntity = userService.getMapper().toEntity(userDto);
        GuildEntity guild = new GuildEntity();
        guild.setName(guildCreate.getName());
        guild.setOwner(userEntity);

        GuildEntity saved = guildRepository.save(guild);

        TransactionSynchronizationManager.registerSynchronization(
                new TransactionSynchronization() {
                    @Override
                    public void afterCommit() {
                        eventPublisher.publishEvent(
                                new GuildCreatedEvent(saved.getId(), guildCreate.getOwnerId())
                        );
                    }
                }
        );

        return mapper.toDto(saved);
    }


}
