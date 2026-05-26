package com.app.guild.data.entity;

import com.app.channel.entity.ChannelEntity;
import com.app.member.entity.MemberEntity;
import com.app.role.entity.RoleEntity;
import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;
import java.util.List;

@Entity
@Table(
        name = "guilds",
        indexes = {
                @Index(name = "idx_guild_name", columnList = "guild_name"),
                @Index(name = "idx_owner_id", columnList = "owner_id"),
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

    @Builder.Default
    @Column(name = "is_public")
    private boolean isPublic = true;

    @Builder.Default
    @Column(name = "invite_only")
    private boolean inviteOnly = false;

    @Builder.Default
    @Column(name = "member_count")
    private Integer memberCount = 0;

    @Builder.Default
    @Column(name = "channel_count")
    private Integer channelCount = 0;

    @Builder.Default
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
    @OneToMany(mappedBy = "guild")
    private List<MemberEntity> members;

    @OneToMany(mappedBy = "guild")
    private List<RoleEntity> roles;

    @OneToMany(mappedBy = "guild")
    private List<ChannelEntity> channels;

    @PreUpdate
    public void onUpdate() {
        updatedAt = LocalDateTime.now();
    }
}