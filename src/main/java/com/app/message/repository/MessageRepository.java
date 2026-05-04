package com.app.message.repository;

import com.app.message.data.entity.MessageEntity;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface MessageRepository extends JpaRepository<MessageEntity, Long> {

    Optional<MessageEntity> findById(Long id);
    @Query("""
SELECT m
FROM MessageEntity m
WHERE m.channelId = :channelId
ORDER BY m.createdAt DESC
""")
    List<MessageEntity> getGeneralMessages(
            @Param("channelId") Long channelId,
            Pageable pageable
    );
}
