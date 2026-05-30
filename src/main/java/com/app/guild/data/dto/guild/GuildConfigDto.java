package com.app.guild.data.dto.guild;

import lombok.Data;

@Data
public class GuildConfigDto {
    private boolean isPublic;
    private boolean inviteOnly;

    private Integer verificationLevel;
    private Integer mfaLevel;

    private String preferredLocale;
    private Integer maxMembers;

}
