package com.app.listener.guild;

import com.app.channel.service.ChannelService;
import com.app.event.guild.GuildCreatedEvent;
import com.app.guild.service.GuildApplicationService;
import com.app.member.service.MemberService;
import com.app.role.service.RoleService;
import org.springframework.stereotype.Component;
import org.springframework.transaction.event.TransactionPhase;
import org.springframework.transaction.event.TransactionalEventListener;

@Component
public class GuildEventListener {

    private final GuildApplicationService guildApplicationService;
    public GuildEventListener(GuildApplicationService guildApplicationService
    ) {
        this.guildApplicationService = guildApplicationService;
    }

    @TransactionalEventListener(phase = TransactionPhase.AFTER_COMMIT)
    public void handleGuildCreated(GuildCreatedEvent event) {
        guildApplicationService.handleGuildCreation(event.getGuildId(), event.getUserId());
    }
}