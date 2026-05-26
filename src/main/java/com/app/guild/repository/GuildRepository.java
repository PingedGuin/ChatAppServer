package com.app.guild.repository;

import com.app.guild.data.Entity.GuildEntity;
import com.app.guild.data.dto.guild.GuildInfoDto;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface GuildRepository extends JpaRepository <GuildEntity, Integer> {
    Optional<GuildEntity> findByGuildId(Long guildId);

    List<GuildInfoDto> getUserGuilds(Long userId);
}
