package com.app.policy.policies.guild.ban.context;

import com.app.policy.data.PolicyContext;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import java.time.Instant;

@EqualsAndHashCode(callSuper = true)
@Data
@AllArgsConstructor
@NoArgsConstructor
public class BanPolicyContext extends PolicyContext {
    Long adminId;
    Long targetedUserId;
    String reason;
    private Instant banTime;
}
