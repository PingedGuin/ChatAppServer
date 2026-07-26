package com.app.template.data.entity;

import com.app.template.data.dto.ChannelType;
import jakarta.persistence.*;
import lombok.Data;

@Entity
@Table(name = "template_channels")
@Data
public class TemplateChannelEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;

    private Integer position;

    @Enumerated(EnumType.STRING)
    private ChannelType type;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "template_id")
    private GuildTemplateEntity template;
}
