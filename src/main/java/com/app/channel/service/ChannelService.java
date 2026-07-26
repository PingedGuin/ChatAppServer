package com.app.channel.service;

import com.app.channel.entity.ChannelEntity;
import com.app.channel.repository.ChannelRepository;
import com.app.common.id.SnowflakeIdService;
import com.app.guild.data.entity.GuildEntity;
import com.app.guild.permission.data.dto.ChannelPermsDto;
import com.app.guild.permission.engine.PermissionService;
import com.app.guild.service.GuildService;
import com.app.member.entity.MemberOverride;
import com.app.message.data.dto.chat.command.ChatRequest;
import com.app.message.service.WebSocketService;
import com.app.role.entity.RoleOverride;
import com.app.template.data.dto.ChannelType;
import com.github.benmanes.caffeine.cache.Cache;
import com.github.benmanes.caffeine.cache.Caffeine;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.Map;
import java.util.Optional;
import java.util.concurrent.TimeUnit;
import java.util.stream.Collectors;

@Slf4j
@Service
public class ChannelService {
    private final ChannelRepository channelRepository;
    private final WebSocketService webSocketService;
    private final GuildService guildService;
    private final PermissionService permissionService;
    private final SnowflakeIdService idService;

    public ChannelService(ChannelRepository repository, WebSocketService webSocketService, GuildService guildService, PermissionService permissionService, SnowflakeIdService idService) {
        this.channelRepository = repository;
        this.webSocketService = webSocketService;
        this.guildService = guildService;
        this.permissionService = permissionService;
        this.idService = idService;
    }

    private final Cache<Long, ChannelEntity> cacheEntity = Caffeine.newBuilder()
            .maximumSize(50000)
            .expireAfterWrite(10, TimeUnit.MINUTES)
            .recordStats()
            .build();

    private final Cache<String, Optional<MemberOverride>> memberOverridePermsCache = Caffeine.newBuilder()
            .maximumSize(200_000)
            .expireAfterWrite(10, TimeUnit.MINUTES)
            .recordStats()
            .build();

    private final Cache<Long, Map<Long, RoleOverride>> cacheOverrideRolesPerms = Caffeine.newBuilder()
            .maximumSize(50000)
            .expireAfterWrite(10, TimeUnit.MINUTES)
            .recordStats()
            .build();


    public ChannelPermsDto getChannelPermissions(Long guildId, Long channelId, Long memberId) {
        String key = String.format("channel:%s:%s", guildId, channelId);

        ChannelEntity channel = cacheEntity.get(channelId, id -> channelRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Channel not found"))
        );

        String memberPermskey = "member:" + channelId + ":" + memberId;
        Optional<MemberOverride> memberOverride = memberOverridePermsCache.get(memberPermskey, k ->
                channelRepository.findByChannelIdAndMemberId(channel.getId(), memberId)
        );
        Map<Long, RoleOverride> roleOverrides = cacheOverrideRolesPerms.get(channelId, k ->
                channel.getRoleOverrides().stream()
                        .collect(Collectors.toMap(
                                ro -> ro.getRole().getId(),
                                ro -> ro
                        ))
        );

        return new ChannelPermsDto(channel.getGuild().getId(), channel.getId(), roleOverrides, memberOverride.orElse(null));
    }

    public void checkUserPerms(ChatRequest request) {
        if (request == null) {
            log.error("request is null");
            return;
        }
        Long guildId = request.getGuildId();
        Long channelId = request.getChannelId();
        Long userId = request.getUserId();

//        var channelPerms = getChannelPermissions(guildId, channelId, userId);
//        var memberPerms = guildService.getMemberPerms(guildId, userId);
//        if (permissionService.isMemberAllowed(channelPerms,memberPerms)){
//
//        }

    }

    public void evictChannel(Long channelId) {
      //  cacheEntity.invalidate("channel:" + guildId + ":" + channelId);
        cacheEntity.invalidate(channelId);
        cacheOverrideRolesPerms.invalidate(channelId);

        memberOverridePermsCache.asMap().keySet()
                .removeIf(k -> k.startsWith("member:" + channelId + ":"));
    }

    public void createChannel(Long guildId, String name, ChannelType type, Integer position) {
        GuildEntity guild = guildService.getGuildEntityById(guildId);
        ChannelEntity channel = ChannelEntity.builder()
                .id(idService.generate())
                .name(name)
                .guild(guild)
                .build();

        channelRepository.save(channel);

    }
}
