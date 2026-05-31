package com.app.user.repository;

import com.app.guild.data.entity.GuildEntity;
import com.app.user.data.entity.UserInfoEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;

public interface UserInfoRepository extends JpaRepository<UserInfoEntity, Long> {

    Optional<UserInfoEntity> findByUsername(String username);

    Optional<UserInfoEntity> findByEmail(String email);

    boolean existsByEmail(String email);

    boolean existsByUsername(String username);

    @Query("""
                SELECT m.guild
                FROM MemberEntity m
                WHERE m.userInfo.id = :userId
            """)
    List<GuildEntity> findUserGuilds(@Param("userId") Long userId);
}