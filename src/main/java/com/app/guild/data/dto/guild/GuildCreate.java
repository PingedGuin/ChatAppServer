package com.app.guild.data.dto.guild;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class GuildCreate {
    private Long ownerId;
    private String name;
}
