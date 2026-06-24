package com.app.workflow.step;

import com.app.workflow.data.model.step.StepResult;
import com.app.workflow.data.model.workflow.WorkflowContext;
import com.app.workflow.data.model.workflow.WorkflowDefinition;
import com.app.workflow.data.model.workflow.WorkflowInstance;
import com.app.workflow.data.model.workflow.WorkflowStep;

public class CreateChannelStep
        implements WorkflowStep {
    @Override
    public StepResult execute(WorkflowDefinition definition, WorkflowContext context, WorkflowInstance instance) {
        return StepResult.failure("Not implemented");
    }
}