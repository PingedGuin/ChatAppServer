package com.app.template.data.entity;

import jakarta.persistence.*;
import lombok.Data;

@Entity
@Table(name = "guild_templates")
@Data
public class GuildTemplateEntity {

    @Id
    private Long id;

    private String name;

    @OneToMany(
            mappedBy = "template",
            cascade = CascadeType.ALL
    )
    private List<TemplateChannelEntity> channels;
}
