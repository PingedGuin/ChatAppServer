package com.app.workflow.step.guild;

import com.app.guild.data.dto.guild.GuildCreateRequest;
import com.app.guild.data.dto.guild.GuildInfoDto;
import com.app.guild.service.GuildService;
import com.app.workflow.data.model.step.StepResult;
import com.app.workflow.data.model.workflow.*;
import com.app.workflow.annotation.Step;
import com.app.workflow.data.model.workflow.context.WorkflowContext;
import com.app.workflow.data.model.workflow.context.WorkflowContextKey;
import com.app.workflow.step.StepName;
import org.springframework.stereotype.Component;

@Component
@Step(name = StepName.CREATE_GUILD)
public class CreateGuildStep implements WorkflowStep {
    private final GuildService guildService;

    public CreateGuildStep(GuildService guildService) {
        this.guildService = guildService;
    }

    @Override
    public StepResult execute(WorkflowDefinition definition,WorkflowContext context, WorkflowInstance instance) {
        GuildCreateRequest guild = context.get(WorkflowContextKey.CREATE_GUILD_REQUEST, GuildCreateRequest.class);
        GuildInfoDto guildInfoDto = GuildInfoDto.builder()
                .guildName(guild.getName())
                .ownerId(guild.getOwnerId())
                .build();

        GuildInfoDto guildInfo = guildService.createGuild(guildInfoDto);
        context.put(WorkflowContextKey.GUILD_INFO, guildInfo);

        return StepResult.success();
    }
}
