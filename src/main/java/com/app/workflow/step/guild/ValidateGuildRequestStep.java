package com.app.workflow.step.guild;

import com.app.guild.data.dto.guild.GuildCreateRequest;
import com.app.user.service.UserService;
import com.app.workflow.annotation.Step;
import com.app.workflow.data.model.step.StepResult;
import com.app.workflow.data.model.workflow.*;
import com.app.workflow.data.model.workflow.context.WorkflowContext;
import com.app.workflow.data.model.workflow.context.WorkflowContextKey;
import com.app.workflow.data.model.workflow.error.WorkflowError;
import com.app.workflow.data.model.workflow.error.WorkflowErrorCode;
import com.app.workflow.step.StepName;
import org.springframework.stereotype.Component;

@Component
@Step(name = StepName.VALIDATE_GUILD_REQUEST)
public class ValidateGuildRequestStep implements WorkflowStep {
    private final UserService userService;

    public ValidateGuildRequestStep(UserService userService) {
        this.userService = userService;
    }

    @Override
    public StepResult execute(WorkflowDefinition definition, WorkflowContext context, WorkflowInstance instance) {
        var request = context.get(WorkflowContextKey.CREATE_GUILD_REQUEST, GuildCreateRequest.class);
        if (!request.isValid()){
            return StepResult.failure(WorkflowError
                    .builder().code(WorkflowErrorCode.INVALID_DATA)
                    .build()
            );
        }

        if(userService.reachedGuildsLimit(request.getOwnerId())){
            return StepResult.failure(WorkflowError
                    .builder().code(WorkflowErrorCode.REACHED_GUILD_LIMIT)
                    .build()
            );
        }
        return StepResult.success();
    }
}

// TODO: Check for invalid characters in name

// TODO: Check reserved/prohibited names (e.g. admin, system)
// TODO: Check user permissions to create guild

// TODO: Apply rate limiting (max guilds per user / time window)

// TODO: Return specific error codes for each validation failure
