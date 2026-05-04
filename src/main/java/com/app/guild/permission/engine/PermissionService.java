package com.app.guild.permission.engine;

import com.app.channel.data.ChannelDto;
import com.app.guild.permission.data.Permission;
import com.app.guild.permission.data.dto.ChannelPermsDto;
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

    private final Cache<String, Long> cache = Caffeine.newBuilder()
            .maximumSize(100_000)
            .expireAfterWrite(15, TimeUnit.MINUTES)
            .build();

    public Long getPermissions(ChannelPermsDto channelPerms,MemberPermissionDto memberPerms) {
        String cacheKey = String.format("perm:%s:%s:%s",
                memberPerms.getGuildId(),
                channelPerms.getChannelId(),
                memberPerms.getUserId());

        Long cachedPerm = cache.getIfPresent(cacheKey);
        if (cachedPerm != null) return cachedPerm;

      //  MemberPermissionDto member = memberService.getMemberContext(memberBasicData.getUserId(), memberBasicData.getGuildId());
      //  channelPerms = channelService.getChannelPermissions(member.getGuildId(), channel.getChannelId(), member.getUserId());
        Long perm = calculatePermissions(memberPerms, channelPerms);
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
    public boolean isMemberAllowed(ChannelPermsDto channelPerms,MemberPermissionDto memberPerms) {
        return true;
    }
    public void evictPermissions(String guildId, Long channelId, Long userId) {
        cache.invalidate("perm:" + guildId + ":" + channelId + ":" + userId);
    }
}