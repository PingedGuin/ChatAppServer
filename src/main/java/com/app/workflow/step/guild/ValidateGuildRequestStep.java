package com.app.workflow.step.guild;

import com.app.guild.data.dto.guild.GuildCreateRequest;
import com.app.workflow.annotation.Step;
import com.app.workflow.data.model.step.StepResult;
import com.app.workflow.data.model.workflow.*;
import com.app.workflow.step.StepName;


@Step(name = StepName.VALIDATE_GUILD_REQUEST)
public class ValidateGuildRequestStep implements WorkflowStep {
    @Override
    public StepResult execute(WorkflowDefinition definition, WorkflowContext context, WorkflowInstance instance) {
        var request = context.get(WorkflowContextKey.CREATE_GUILD_REQUEST, GuildCreateRequest.class);
        if (!request.isValid()){
            return StepResult.failure(WorkflowError
                    .builder().code(WorkflowErrorCode.INVALID_DATA)
                    .build()
            );
        }

        return null;
    }
}
