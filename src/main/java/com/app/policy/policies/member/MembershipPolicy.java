package com.app.policy.policies.member;

import com.app.policy.Policy;
import com.app.policy.data.PolicyContext;

public class MembershipPolicy implements Policy {
    @Override
    public boolean check(PolicyContext context) {

        return false;
    }
}
