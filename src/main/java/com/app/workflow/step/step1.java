package com.app.workflow.step;

import com.app.workflow.data.model.StepResult;
import com.app.workflow.data.model.WorkflowContext;
import com.app.workflow.data.model.WorkflowDefinition;
import com.app.workflow.data.model.WorkflowStep;
import com.app.workflow.annotation.Step;

@Step(stepName = "step1")
public class step1 implements WorkflowStep {
    @Override
    public StepResult execute(WorkflowDefinition definition, WorkflowContext context) {
        System.out.println("Step 1 executed");
        return StepResult.success();
    }
}
