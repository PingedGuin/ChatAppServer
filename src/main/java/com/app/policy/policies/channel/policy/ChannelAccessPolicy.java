package com.app.policy.policies.channel.policy;

import com.app.policy.Action;
import com.app.policy.Policy;
import com.app.policy.Priority;
import com.app.policy.annotation.PolicyType;
import com.app.policy.data.PolicyContext;

@PolicyType(action = Action.MANAGE_CHANNELS, priority = Priority.HIGH)
public class ChannelAccessPolicy implements Policy {
    @Override
    public boolean check(PolicyContext context) {

        return false;
    }
}
