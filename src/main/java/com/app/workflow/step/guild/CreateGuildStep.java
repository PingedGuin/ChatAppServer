package com.app.workflow.step.guild;

import com.app.guild.data.dto.guild.GuildInfoDto;
import com.app.guild.service.GuildService;
import com.app.workflow.data.model.step.StepResult;
import com.app.workflow.data.model.workflow.*;
import com.app.workflow.annotation.Step;
import com.app.workflow.data.model.workflow.context.WorkflowContext;
import com.app.workflow.data.model.workflow.context.WorkflowContextKey;
import com.app.workflow.step.StepName;
import com.app.workflow.step.guild.context.CreateGuildContext;
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
        CreateGuildContext guild = context.get(WorkflowContextKey.GUILD_CONTEXT, CreateGuildContext.class);
        GuildInfoDto guildInfoDto = GuildInfoDto.builder()
                .guildName(guild.getRequest().getName())
                .ownerId(guild.getRequest().getOwnerId())
                .build();

        GuildInfoDto createdGuild = guildService.createGuild(guildInfoDto);
        guild.setGuild(createdGuild);

        context.put(WorkflowContextKey.GUILD_CONTEXT, guild);
        return StepResult.success();
    }
}
