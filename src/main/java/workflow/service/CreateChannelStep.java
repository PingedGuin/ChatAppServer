package workflow.service;

import workflow.data.model.StepResult;
import workflow.data.model.WorkflowContext;
import workflow.data.model.WorkflowDefinition;
import workflow.data.model.WorkflowStep;

public class CreateChannelStep
        implements WorkflowStep {
    @Override
    public StepResult execute(WorkflowDefinition definition, WorkflowContext context) {
        return null;
    }
}