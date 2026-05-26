package com.app.channel.entity;

import com.app.guild.data.entity.GuildEntity;
import com.app.member.entity.MemberOverride;
import com.app.role.entity.RoleOverride;
import jakarta.persistence.*;
import lombok.Data;

import java.util.List;

@Entity
@Table(name = "channels")
@Data
public class ChannelEntity {

    @Id
    private Long id;

    @Column(nullable = false)
    private String name;

    @OneToMany(mappedBy = "channel", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private List<RoleOverride> roleOverrides;

    @OneToMany(mappedBy = "channel", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private List<MemberOverride> memberOverrides;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "guild_id", nullable = false)
    private GuildEntity guild;
}