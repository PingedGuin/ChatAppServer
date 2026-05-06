package com.app.policy;


import com.app.policy.data.PolicyContext;

public interface Policy {
    public boolean check(PolicyContext context);

}
