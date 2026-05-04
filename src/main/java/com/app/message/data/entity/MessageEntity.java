package com.app.message.data.entity;

import jakarta.persistence.*;
import lombok.Data;

import java.time.Instant;

@Entity
@Table(name = "message")
@Data
public class MessageEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String content;

    @Column(name = "guild_id")
    private Long guildId;

    @Column(name = "channel_id")
    private Long channelId;

    @Column(name = "senderId")
    private Long senderId;

    @Column(name = "created_at")
    private Instant createdAt;

}