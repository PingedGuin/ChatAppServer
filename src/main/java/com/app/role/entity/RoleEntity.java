package com.app.role.entity;

import com.app.guild.data.entity.GuildEntity;
import com.app.member.entity.MemberEntity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

@Entity
@Table(
        name = "roles",
        uniqueConstraints = {
                @UniqueConstraint(
                        columnNames = {"guild_id", "name"}
                )
        }
)
@Getter
@Setter
public class RoleEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, length = 100)
    private String name;

    @Column(nullable = false)
    private long permissions;

    @Column(nullable = false)
    private int position;

    private String color;


    private boolean mentionable = true;

    private boolean hoisted = false;

    @ManyToMany(mappedBy = "roles")
    private List<MemberEntity> members = new ArrayList<>();

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "guild_id", nullable = false)
    private GuildEntity guild;
}