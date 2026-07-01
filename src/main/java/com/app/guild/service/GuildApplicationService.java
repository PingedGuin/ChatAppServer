package com.app.guild.service;

import com.app.channel.service.ChannelService;
import com.app.guild.data.dto.guild.GuildCreateRequest;
import com.app.guild.data.entity.GuildEntity;
import com.app.member.entity.MemberEntity;
import com.app.member.service.MemberService;
import com.app.role.service.RoleService;
import com.app.user.data.entity.UserEntity;
import com.app.user.service.UserService;
import com.app.workflow.data.dto.StartWorkflowRequest;
import com.app.workflow.service.WorkflowService;
import com.app.workflow.step.StepName;
import org.springframework.stereotype.Service;

@Service
public class GuildApplicationService {
    private final UserService userService;
    private final GuildService guildService;
    private final RoleService roleService;
    private final ChannelService channelService;
    private final MemberService memberService;
    private final WorkflowService workflowService;

    public GuildApplicationService(UserService userService, GuildService guildService, RoleService roleService,
                                   ChannelService channelService, MemberService memberService, WorkflowService workflowService) {
        this.userService = userService;
        this.guildService = guildService;
        this.roleService = roleService;
        this.channelService = channelService;
        this.memberService = memberService;
        this.workflowService = workflowService;
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
    public void handleGuildCreation(GuildCreateRequest guildCreateContext) {
        StartWorkflowRequest request = StartWorkflowRequest.builder()
                .workflowName(StepName.CREATE_GUILD)
                .build();

        workflowService.startWorkflow(request);
    }
}
