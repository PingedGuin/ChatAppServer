package com.app.policy.data;

import com.app.policy.Action;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class PolicyContext {
    private Long userId;
    private Long channelId;
    private Long guildId;
    private Action action;
}
