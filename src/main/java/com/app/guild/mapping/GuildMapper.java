package com.app.guild.mapping;

import com.app.guild.data.entity.GuildEntity;
import com.app.guild.data.dto.guild.GuildInfoDto;
import org.mapstruct.Mapper;
import org.mapstruct.MappingTarget;

@Mapper(componentModel = "spring")
public interface GuildMapper {
    GuildInfoDto toDto(GuildEntity entity);
    void updateEntityFromDto(GuildInfoDto dto, @MappingTarget GuildEntity entity);

}
