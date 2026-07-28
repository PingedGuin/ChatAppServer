package com.app.channel.entity;

import com.app.guild.data.entity.GuildEntity;
import com.app.member.entity.MemberOverride;
import com.app.role.entity.RoleOverride;
import com.app.template.data.dto.ChannelType;
import jakarta.persistence.*;
import lombok.*;

import java.util.ArrayList;
import java.util.List;

@Builder
@Entity
@Table(name = "channels")
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class ChannelEntity {

    @Id
    @Column(nullable = false, updatable = false)
    private Long id;

    @Column(nullable = false)
    private String name;

    @Builder.Default
    @OneToMany(mappedBy = "channel", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private List<RoleOverride> roleOverrides = new ArrayList<>();

    @Column(nullable = false)
    ChannelType type;

    Integer position;

    @Builder.Default
    @OneToMany(mappedBy = "channel", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private List<MemberOverride> memberOverrides = new ArrayList<>();

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "guild_id", nullable = false)
    private GuildEntity guild;

}