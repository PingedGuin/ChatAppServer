package com.app.channel.entity;

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

    @Column(nullable = false)
    private String guildId;
}