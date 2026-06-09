package com.app.member.repository;

import com.app.guild.data.entity.GuildEntity;
import com.app.member.entity.MemberEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface MemberRepository extends JpaRepository<MemberEntity, Long> {
    List<MemberEntity> findByUserInfo_Id(Long userId);
    @Query("""
        SELECT m.guild
        FROM MemberEntity m
        WHERE m.userInfo.id = :userId
    """)
    List<GuildEntity> findGuildsByUserId(Long userId);

}
