package com.app.guild.permission.engine;

import com.app.channel.data.ChannelDto;
import com.app.channel.service.ChannelService;
import com.app.guild.permission.data.Permission;
import com.app.guild.permission.data.dto.ChannelPermsDto;
import com.app.member.dto.MemberDto;
import com.app.member.service.MemberService;
import com.app.role.dto.RoleDto;
import com.app.role.entity.RoleOverride;
import com.app.user.data.dto.MemberPermissionDto;
import com.github.benmanes.caffeine.cache.Cache;
import com.github.benmanes.caffeine.cache.Caffeine;
import org.springframework.stereotype.Service;

import java.util.Set;
import java.util.concurrent.TimeUnit;

@Service
public class PermissionService {
    private final ChannelService channelService;
    private final MemberService memberService;

    private final Cache<String, Long> cache = Caffeine.newBuilder()
            .maximumSize(100_000)
            .expireAfterWrite(15, TimeUnit.MINUTES)
            .build();

    public PermissionService(ChannelService channelService, MemberService memberService) {
        this.channelService = channelService;
        this.memberService = memberService;
    }

    public Long getPermissions(MemberDto memberBasicData, ChannelDto channel) {
        String cacheKey = String.format("perm:%s:%s:%s",
                memberBasicData.getGuildId(),
                channel.getChannelId(),
                memberBasicData.getUserId());

        Long cachedPerm = cache.getIfPresent(cacheKey);
        if (cachedPerm != null) return cachedPerm;

        MemberPermissionDto member = memberService.getMemberContext(memberBasicData.getUserId(), memberBasicData.getGuildId());
        var channelContext = channelService.getChannelPermissions(member.getGuildId(), channel.getChannelId(), member.getUserId());
        Long perm = calculatePermissions(member, channelContext);
        cache.put(cacheKey, perm);
        return perm;
    }

    private long calculatePermissions(MemberPermissionDto member, ChannelPermsDto channel) {

        long effectivePermissions = 0L;

        for (RoleDto role : member.getRoles()) {
            effectivePermissions |= role.getPermission();
        }

        if ((effectivePermissions & Permission.ADMINISTRATOR.getBit()) != 0) {
            return ~0L;
        }
        Set<Long> roleIds = member.getRoleIds();
        for (Long roleId : roleIds) {
            RoleOverride override = channel.getRoleOverrideMap().get(roleId);

            if (override == null) continue;

            effectivePermissions &= ~override.getDeny();
            effectivePermissions |= override.getAllow();
        }

        if (channel.getMemberOverride() != null) {
            effectivePermissions &= ~channel.getMemberOverride().getDeniedPermissions();
            effectivePermissions |= channel.getMemberOverride().getAllowedPermissions();
        }

        return effectivePermissions;
    }

    public boolean isMemberAllowed() {

        return false;
    }

    public boolean isMemberAllowed(ChannelPermsDto channelPerms,MemberPermissionDto memberPerms) {
        return true;
    }
}