package dev.dre.chatappserver.database.repository;

import dev.dre.chatappserver.database.entitys.UserSessionEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UserSessionRepository extends JpaRepository<UserSessionEntity, Long> {
    Optional<UserSessionEntity> findBySessionId(String sessionId);

    Optional<UserSessionEntity> findByUserId(String userId);
}
