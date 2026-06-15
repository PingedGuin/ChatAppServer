package workflow.service;

import workflow.data.dto.StepResult;
import workflow.data.model.WorkflowContext;
import workflow.data.model.WorkflowStep;

public class CreateChannelStep
        implements WorkflowStep {

    @Override
    public StepResult execute(
            WorkflowContext context
    ) {

        // create channel

        return StepResult.success();
    }
}