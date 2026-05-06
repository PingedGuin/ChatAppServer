package com.app.role.entity;

import com.app.channel.entity.ChannelEntity;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name = "role_overrides")
@Getter
@Setter
public class RoleOverride {

    @Id
    @GeneratedValue
    private Long id;

    @ManyToOne
    @JoinColumn(name = "channel_id")
    private ChannelEntity channel;

    @ManyToOne
    @JoinColumn(name = "role_id")
    private RoleEntity role;

    private Long allow; // bitmask (Allowed roles .
    private Long deny; // denied roles
}
