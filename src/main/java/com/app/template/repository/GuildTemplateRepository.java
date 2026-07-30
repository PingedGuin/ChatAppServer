package com.app.template.repository;

import com.app.template.data.entity.GuildTemplateEntity;
import org.jspecify.annotations.NonNull;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface GuildTemplateRepository extends JpaRepository<GuildTemplateEntity, Long> {
    Optional<GuildTemplateEntity> findByName(String name);
}
