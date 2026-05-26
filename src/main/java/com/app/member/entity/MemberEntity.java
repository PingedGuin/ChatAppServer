package com.app.member.entity;

import com.app.guild.data.entity.GuildEntity;
import com.app.role.entity.RoleEntity;
import com.app.user.data.entity.UserInfoEntity;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(
        name = "members",
        uniqueConstraints = {
                @UniqueConstraint(
                        columnNames = {"guild_id", "user_id"}
                )
        },
        indexes = {
                @Index(name = "idx_member_guild", columnList = "guild_id"),
                @Index(name = "idx_member_user", columnList = "user_id"),
                @Index(name = "idx_member_guild_user", columnList = "guild_id, user_id"),
                @Index(name = "idx_member_guild_banned", columnList = "guild_id, banned")
        }
)
@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class MemberEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(length = 100)
    private String username;

    @Column(length = 100)
    private String nickname;

    private String avatar;

    @Builder.Default
    private boolean owner = false;

    @Builder.Default
    private boolean muted = false;

    @Builder.Default
    private boolean banned = false;

    private LocalDateTime joinedAt;

    private LocalDateTime lastActiveAt;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id", nullable = false)
    private UserInfoEntity userInfo;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "guild_id", nullable = false)
    private GuildEntity guild;

    @ManyToMany(fetch = FetchType.LAZY)
    @JoinTable(
            name = "member_roles",
            joinColumns = @JoinColumn(name = "member_id"),
            inverseJoinColumns = @JoinColumn(name = "role_id")
    )
    private List<RoleEntity> roles = new ArrayList<>();

    @PrePersist
    public void onCreate() {
        joinedAt = LocalDateTime.now();
        lastActiveAt = LocalDateTime.now();
    }
}