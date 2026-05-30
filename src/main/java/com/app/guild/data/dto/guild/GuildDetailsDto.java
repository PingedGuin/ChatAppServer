package com.app.guild.data.dto.guild;

import com.app.channel.data.ChannelDto;
import com.app.member.dto.MemberDto;
import com.app.role.dto.RoleDto;
import lombok.Data;
import java.util.List;
@Data
public class GuildDetailsDto {
    private Long id;
    private String guildName;
    private String description;

    private String guildIcon;
    private String guildBanner;

    private Long ownerId;

    private List<RoleDto> roles;
    private List<ChannelDto> channels;
    private List<MemberDto> members;
}