package com.app.workflow.step.guild.context;

import com.app.channel.data.ChannelDto;
import com.app.guild.data.dto.guild.GuildCreateRequest;
import com.app.guild.data.dto.guild.GuildInfoDto;
import com.app.role.dto.RoleDto;
import lombok.Builder;
import lombok.Data;

import java.util.List;
@Data
@Builder
public class CreateGuildContext {

    private GuildCreateRequest request;

    private GuildInfoDto guild;

    private List<RoleDto> createdRoles;

    private List<ChannelDto> createdChannels;
}