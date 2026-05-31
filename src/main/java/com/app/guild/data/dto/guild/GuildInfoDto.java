package com.app.guild.data.dto.guild;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


@Data
@AllArgsConstructor
@NoArgsConstructor
public class GuildInfoDto {
        private Long id;
        private String guildName;
        private String description;
        private String guildIcon;
        private String guildBanner;
        private Long ownerId;
        private Integer memberCount;
        private Integer channelCount;

}
