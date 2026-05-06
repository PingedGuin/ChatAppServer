package com.app.policy.policies.guild.ban.policy;

import com.app.policy.Action;
import com.app.policy.Policy;
import com.app.policy.Priority;
import com.app.policy.annotation.PolicyType;
import com.app.policy.data.PolicyContext;

@PolicyType(action = Action.BAN,priority = Priority.MEDIUM)
public class BanPolicy implements Policy {
    @Override
    public boolean check(PolicyContext context) {
        return false;
    }
}
