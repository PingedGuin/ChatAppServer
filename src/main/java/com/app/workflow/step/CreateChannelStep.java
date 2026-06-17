package com.app.workflow.step;

import com.app.workflow.data.model.StepResult;
import com.app.workflow.data.model.WorkflowContext;
import com.app.workflow.data.model.WorkflowDefinition;
import com.app.workflow.data.model.WorkflowStep;

public class CreateChannelStep
        implements WorkflowStep {
    @Override
    public StepResult execute(WorkflowDefinition definition, WorkflowContext context) {
        return StepResult.failure("Not implemented");
    }
}