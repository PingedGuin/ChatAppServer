package com.app.workflow.step.guild;

import com.app.workflow.annotation.Step;
import com.app.workflow.data.model.step.StepResult;
import com.app.workflow.data.model.workflow.WorkflowContext;
import com.app.workflow.data.model.workflow.WorkflowDefinition;
import com.app.workflow.data.model.workflow.WorkflowInstance;
import com.app.workflow.data.model.workflow.WorkflowStep;
import com.app.workflow.step.StepName;

@Step(name = StepName.VALIDATE_GUILD_REQUEST)
public class ValidateGuildRequestStep implements WorkflowStep {
    @Override
    public StepResult execute(WorkflowDefinition definition, WorkflowContext context, WorkflowInstance instance) {
        return null;
    }
}
