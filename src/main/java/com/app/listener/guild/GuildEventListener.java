package com.app.listener.guild;

import com.app.channel.service.ChannelService;
import com.app.event.guild.GuildCreatedEvent;
import com.app.member.service.MemberService;
import com.app.role.service.RoleService;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Component;

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

    @EventListener
    public void handleGuildCreated(GuildCreatedEvent event) {

        Long guildId = event.getGuildId();
        Long ownerId = event.getOwnerId();

        memberService.addOwner(ownerId, guildId);

        roleService.createDefaultRole(guildId);

        channelService.createGeneralChannel(guildId);
    }
}