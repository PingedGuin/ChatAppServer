package com.app.policy.policies.channel.join.context;

import com.app.policy.Action;
import com.app.policy.data.PolicyContext;
import lombok.Data;
import lombok.EqualsAndHashCode;

@EqualsAndHashCode(callSuper = true)
@Data
public class JoinChannelContext extends PolicyContext {
    private Long permissions;

    public JoinChannelContext(Long permissions) {
        super(Action.JOIN_CHANNEL);
        this.permissions = permissions;
    }
}
