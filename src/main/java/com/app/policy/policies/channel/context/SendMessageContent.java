package com.app.policy.policies.channel.context;

import com.app.policy.data.PolicyContext;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.time.Instant;

@EqualsAndHashCode(callSuper = true)
@Data
public class SendMessageContent extends PolicyContext {
    private String content;
    private Long senderId;
    private Long permissions;
    private Instant createdAt;
}
