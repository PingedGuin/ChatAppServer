package com.app.workflow.step;

import com.app.workflow.data.model.step.StepResult;
import com.app.workflow.data.model.workflow.WorkflowContext;
import com.app.workflow.data.model.workflow.WorkflowDefinition;
import com.app.workflow.data.model.workflow.WorkflowStep;
import com.app.workflow.annotation.Step;

@Step(stepName = "CreateGuildMember")
public class CreateGuildMember implements WorkflowStep {
    @Override
    public StepResult execute(WorkflowDefinition definition, WorkflowContext context) {
        System.out.println("Step 2 executed");
        return StepResult.success();
    }
}
