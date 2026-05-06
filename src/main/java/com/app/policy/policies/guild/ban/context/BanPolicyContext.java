package com.app.policy.policies.guild.ban.context;

import com.app.policy.Action;
import com.app.policy.data.PolicyContext;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.time.Instant;

@EqualsAndHashCode(callSuper = true)
@Data
public class BanPolicyContext extends PolicyContext {
    Long adminId;
    Long targetedUserId;
    String reason;
    private Instant banTime;

    public BanPolicyContext(Long adminId, Long targetedUserId, String reason, Instant banTime) {
        super(Action.BAN);
        this.adminId = adminId;
        this.targetedUserId = targetedUserId;
        this.reason = reason;
        this.banTime = banTime;
    }
}
