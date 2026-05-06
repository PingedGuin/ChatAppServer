package com.app.policy.policies.channel.context;

import com.app.policy.data.PolicyContext;
import lombok.Data;
import lombok.EqualsAndHashCode;

@EqualsAndHashCode(callSuper = true)
@Data
public class JoinChannelContext extends PolicyContext {
    private Long permissions;
}
