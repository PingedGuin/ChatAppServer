package com.app.guild.repository;

import com.app.guild.data.entity.GuildEntity;
import com.app.guild.data.dto.guild.GuildInfoDto;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface GuildRepository extends JpaRepository<GuildEntity, Long> {

    Optional<GuildEntity> findById(Long id);

   // Optional<GuildEntity> findByGuildName(String name);
}