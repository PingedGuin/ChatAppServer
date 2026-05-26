package com.app.guild.data.entity.command;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.time.Instant;

@Entity
@Table(name = "bans")
@Getter
@Setter
public class BanEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "guild_id", nullable = false)
    private String guildId;

    @Column(name = "user_id", nullable = false)
    private String userId;

    @Column(name = "banned_by", nullable = false)
    private String bannedBy;

    @Column(name = "reason")
    private String reason;

    @Column(name = "created_at", nullable = false)
    private Instant createdAt;
}