package com.app.guild.data.dto.guild;

import com.app.role.dto.RoleDto;
import lombok.Data;

import java.util.List;

@Data
public class GuildInfoDto {
    private Long guildId;
    private String guildName;
    private String guildIcon;
    private List<RoleDto> roles;
}
