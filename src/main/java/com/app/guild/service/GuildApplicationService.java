package com.app.guild.service;

import com.app.guild.data.entity.GuildEntity;
import com.app.member.entity.MemberEntity;
import com.app.member.repository.MemberRepository;
import com.app.user.data.entity.UserEntity;
import com.app.user.service.UserService;
import org.springframework.stereotype.Service;

@Service
public class GuildApplicationService {
    private final UserService userService;
    private final GuildService guildService;
    private final MemberRepository memberRepository;

    public GuildApplicationService(UserService userService, GuildService guildService, MemberRepository memberRepository) {
        this.userService = userService;
        this.guildService = guildService;
        this.memberRepository = memberRepository;
    }
    public void createMemberGuild(Long userId, Long guildId) {

        UserEntity user = userService.getUserEntityById(userId);
        GuildEntity guild = guildService.getGuildEntityById(guildId);

        MemberEntity member = MemberEntity.builder()
                .userInfo(user)
                .guild(guild)
                .build();

        memberRepository.save(member);
    }

}
