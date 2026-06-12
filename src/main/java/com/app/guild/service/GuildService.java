package com.app.guild.service;

import com.app.event.guild.GuildCreatedEvent;
import com.app.guild.data.dto.guild.GuildConfigDto;
import com.app.guild.data.dto.guild.GuildCreate;
import com.app.guild.data.dto.guild.GuildDetailsDto;
import com.app.guild.data.entity.GuildEntity;
import com.app.guild.data.dto.guild.GuildInfoDto;
import com.app.guild.mapping.GuildMapper;
import com.app.guild.repository.GuildRepository;
import com.app.member.entity.MemberEntity;
import com.app.member.repository.MemberRepository;
import com.app.member.service.MemberService;
import com.app.role.dto.RoleDto;
import com.app.member.dto.MemberPermissionDto;
import com.app.user.data.entity.UserEntity;
import com.app.user.service.UserService;
import com.github.benmanes.caffeine.cache.Cache;
import com.github.benmanes.caffeine.cache.Caffeine;
import lombok.Builder;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.transaction.event.TransactionPhase;
import org.springframework.transaction.event.TransactionalEventListener;
import org.springframework.transaction.support.TransactionSynchronization;
import org.springframework.transaction.support.TransactionSynchronizationManager;

import java.util.List;
import java.util.concurrent.TimeUnit;

@Service
public class GuildService {
    private final GuildRepository guildRepository;
    private final MemberRepository memberRepository;
    private final MemberService memberService;
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


    public GuildService(GuildRepository guildRepository, MemberRepository memberRepository, MemberService memberService, ApplicationEventPublisher eventPublisher, GuildMapper mapper, UserService userService) {
        this.guildRepository = guildRepository;
        this.memberRepository = memberRepository;
        this.memberService = memberService;
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

    public GuildInfoDto createGuild(GuildCreate guildCreate) {
        if (guildCreate == null) throw new IllegalArgumentException("Guild Create cannot be null");

        UserEntity owner = userService.getUserEntityById(guildCreate.getOwnerId());

        GuildEntity guild = GuildEntity.builder()
                .name(guildCreate.getName())
                .owner(owner)
                .build();

        GuildEntity saved = guildRepository.save(guild);

        eventPublisher.publishEvent(new GuildCreatedEvent(guildCreate.getOwnerId(), saved.getId()));
        return mapper.toDto(saved);
    }

    public GuildEntity getGuildEntityById(Long guildId) {
        if (guildId != null) throw new IllegalArgumentException("Guild Id cannot be null");


        return null;
    }
}
