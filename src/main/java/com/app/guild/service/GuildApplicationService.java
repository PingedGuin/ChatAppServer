package com.app.guild.service;

import com.app.channel.service.ChannelService;
import com.app.guild.data.entity.GuildEntity;
import com.app.member.entity.MemberEntity;
import com.app.member.service.MemberService;
import com.app.role.service.RoleService;
import com.app.user.data.entity.UserEntity;
import com.app.user.service.UserService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class GuildApplicationService {
    private final UserService userService;
    private final GuildService guildService;
    private final RoleService roleService;
    private final ChannelService channelService;
    private final MemberService memberService;

    public GuildApplicationService(UserService userService, GuildService guildService, RoleService roleService,
                                   ChannelService channelService, MemberService memberService) {
        this.userService = userService;
        this.guildService = guildService;
        this.roleService = roleService;
        this.channelService = channelService;
        this.memberService = memberService;
    }

    public void addMemberToGuild(Long userId, Long guildId) {

        UserEntity user = userService.getUserEntityById(userId);
        GuildEntity guild = guildService.getGuildEntityById(guildId);

        MemberEntity member = MemberEntity.builder()
                .userInfo(user)
                .guild(guild)
                .build();
        memberService.save(member);
    }

    @Transactional
    public void handleGuildCreation(Long guildId, Long userId) {
        addMemberToGuild(userId, guildId);
        roleService.createDefaultRole(guildId);
        channelService.createGeneralChannel(guildId);
    }
}
