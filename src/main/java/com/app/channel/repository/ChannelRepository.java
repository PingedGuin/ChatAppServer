package com.app.channel.repository;

import com.app.channel.entity.ChannelEntity;
import com.app.member.entity.MemberOverride;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.Optional;

public interface ChannelRepository extends JpaRepository<ChannelEntity, Long> {
    @Query("""
                SELECT mo\s
                FROM MemberOverride mo
                WHERE mo.channel.id = :channelId
                  AND mo.member.id = :memberId
           \s""")
    Optional<MemberOverride> findByChannelIdAndMemberId(
            @Param("channelId") Long channelId,
            @Param("memberId") Long memberId
    );
}

