package com.app.guild.data.Entity;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;

@Entity
@Table(
        name = "guilds",
        indexes = {
                @Index(name = "idx_guild_name", columnList = "guild_name"),
                @Index(name = "idx_owner_id", columnList = "owner_id")
        }
)
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class GuildEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "guild_name", nullable = false, length = 100)
    private String guildName;

    @Column(length = 500)
    private String description;

    @Column(name = "guild_icon")
    private String guildIcon;

    @Column(name = "guild_banner")
    private String guildBanner;

    @Column(name = "owner_id", nullable = false)
    private Long ownerId;

    @Column(name = "is_public")
    private boolean isPublic = true;

    @Column(name = "invite_only")
    private boolean inviteOnly = false;

    @Column(name = "member_count")
    private Integer memberCount = 0;

    @Column(name = "channel_count")
    private Integer channelCount = 0;

    @Column(name = "deleted")
    private boolean deleted = false;

    @Column(name = "created_at", nullable = false, updatable = false)
    private LocalDateTime createdAt;

    @Column(name = "updated_at")
    private LocalDateTime updatedAt;

    @PrePersist
    public void onCreate() {
        createdAt = LocalDateTime.now();
        updatedAt = LocalDateTime.now();
    }

    @PreUpdate
    public void onUpdate() {
        updatedAt = LocalDateTime.now();
    }
}