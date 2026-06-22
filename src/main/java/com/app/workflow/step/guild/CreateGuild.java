package com.app.workflow.step.guild;

import com.app.workflow.data.model.step.StepResult;
import com.app.workflow.data.model.workflow.WorkflowContext;
import com.app.workflow.data.model.workflow.WorkflowDefinition;
import com.app.workflow.data.model.workflow.WorkflowStep;
import com.app.workflow.annotation.Step;
import com.app.workflow.step.StepName;

@Step(name = StepName.CREATE_GUILD)
public class CreateGuild implements WorkflowStep {
    @Override
    public StepResult execute(WorkflowDefinition definition, WorkflowContext context) {
        var thing = "hello";
        context.put("thing", thing);
        System.out.println("Step 1 executed");
        return StepResult.success();
    }
}
