package com.app.guild.permission.data.dto;

import com.app.member.entity.MemberOverride;
import com.app.role.entity.RoleOverride;
import lombok.Data;

import java.util.Map;

@Data
public class ChannelPermsDto {
    String key;
    String channelId;
    Long guildId;
    private Map<Long, RoleOverride> roleOverrideMap;
    private MemberOverride memberOverride;

    public ChannelPermsDto(Long guildId, Long id, Map<Long, RoleOverride> roleOverrideMap, MemberOverride memberOverride) {
        this.key = String.format("channel:%s:%s", guildId, id);
        this.roleOverrideMap = roleOverrideMap;
        this.memberOverride = memberOverride;
    }
}
