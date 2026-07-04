package com.app.workflow.step;

import com.app.workflow.data.model.step.StepResult;
import com.app.workflow.data.model.workflow.*;

public class CreateChannelStep
        implements WorkflowStep {
    @Override
    public StepResult execute(WorkflowDefinition definition, WorkflowContext context, WorkflowInstance instance) {
        return StepResult.failure(WorkflowError
                .builder().code(WorkflowErrorCode.NOT_IMPLEMENTED)
                .build()
        );
    }
}