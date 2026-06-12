package com.app.listener.guild;

import com.app.channel.service.ChannelService;
import com.app.event.guild.GuildCreatedEvent;
import com.app.member.service.MemberService;
import com.app.role.service.RoleService;
import org.springframework.stereotype.Component;
import org.springframework.transaction.event.TransactionPhase;
import org.springframework.transaction.event.TransactionalEventListener;

@Component
public class GuildEventListener {

    private final MemberService memberService;
    private final RoleService roleService;
    private final ChannelService channelService;

    public GuildEventListener(
            MemberService memberService,
            RoleService roleService,
            ChannelService channelService
    ) {
        this.memberService = memberService;
        this.roleService = roleService;
        this.channelService = channelService;
    }

    @TransactionalEventListener(phase = TransactionPhase.AFTER_COMMIT)
    public void handleGuildCreated(GuildCreatedEvent event) {

        memberService.addMemberToGuild(event.getUserId(), event.getGuildId());
        roleService.createDefaultRole(event.getGuildId());
        channelService.createGeneralChannel(event.getGuildId());
    }
}