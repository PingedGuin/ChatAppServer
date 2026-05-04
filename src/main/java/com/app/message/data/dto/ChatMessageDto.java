package com.app.message.data.dto;

import com.app.policy.Action;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.Instant;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ChatMessageDto {
    private Long senderId;
    private Long channelId;
    String channelName;
    private Long guildId;
    private String type;
    private String content;
    private String permission;
    private Action action;
    private Instant CreatedAt;

}
