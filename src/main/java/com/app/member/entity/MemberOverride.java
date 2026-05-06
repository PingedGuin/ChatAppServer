package com.app.member.entity;

import com.app.channel.entity.ChannelEntity;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name = "member_overrides")
@Getter
@Setter
public class MemberOverride {

    @Id
    @GeneratedValue
    private Long id;

    @ManyToOne
    @JoinColumn(name = "channel_id")
    private ChannelEntity channel;

    @ManyToOne
    @JoinColumn(name = "member_id")
    private MemberEntity member;

    @Column(name = "base_perms")
    private Long basePermissions;

    public Long getBasePermissions() {
        return basePermissions != null ? basePermissions : 0L;
    }

    private Long allowedPermissions;
    private Long deniedPermissions;
}