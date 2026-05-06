package com.app.policy.policies.guild;

import com.app.policy.Action;
import com.app.policy.Policy;
import com.app.policy.Priority;
import com.app.policy.annotation.PolicyType;
import com.app.policy.data.PolicyContext;

@PolicyType(action = {
        Action.MANAGE_PERMISSIONS,Action.SEND_MESSAGE}
        ,priority = Priority.MEDIUM)
public class PermissionPolicy implements Policy {
    public boolean check(PolicyContext context) {

        return false;
    }
}
