package com.app.workflow.step.guild.channel;

import com.app.workflow.annotation.Step;
import com.app.workflow.data.model.step.StepResult;
import com.app.workflow.data.model.workflow.*;
import com.app.workflow.data.model.workflow.context.WorkflowContext;
import com.app.workflow.data.model.workflow.error.WorkflowError;
import com.app.workflow.data.model.workflow.error.WorkflowErrorCode;
import com.app.workflow.step.StepName;
import org.springframework.stereotype.Component;

@Component
@Step(name = StepName.CREATE_DEFAULT_CHANNEL)
public class CreateChannelStep implements WorkflowStep {
    @Override
    public StepResult execute(WorkflowDefinition definition, WorkflowContext context, WorkflowInstance instance) {
        return StepResult.failure(WorkflowError
                .builder().code(WorkflowErrorCode.NOT_IMPLEMENTED)
                .build()
        );
    }
}