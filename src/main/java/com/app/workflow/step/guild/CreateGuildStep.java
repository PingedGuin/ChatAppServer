package com.app.workflow.step.guild;

import com.app.workflow.data.model.step.StepResult;
import com.app.workflow.data.model.workflow.*;
import com.app.workflow.annotation.Step;
import com.app.workflow.step.StepName;

@Step(name = StepName.CREATE_GUILD)
public class CreateGuildStep implements WorkflowStep {
    @Override
    public StepResult execute(WorkflowDefinition definition, WorkflowContext context, WorkflowInstance instance) {
        var thing = "hello";
        System.out.println("Step 1 executed");
        return StepResult.success();
    }
}
