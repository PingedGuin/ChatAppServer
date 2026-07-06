package com.app.workflow.step.role.deafult;

import com.app.workflow.annotation.Step;
import com.app.workflow.data.model.step.StepResult;
import com.app.workflow.data.model.workflow.context.WorkflowContext;
import com.app.workflow.data.model.workflow.WorkflowDefinition;
import com.app.workflow.data.model.workflow.WorkflowInstance;
import com.app.workflow.data.model.workflow.WorkflowStep;
import com.app.workflow.step.StepName;

@Step(name = StepName.CREATE_DEFAULT_ROLE)
public class CreateDefaultRolesStep implements WorkflowStep {
    @Override
    public StepResult execute(WorkflowDefinition definition, WorkflowContext context, WorkflowInstance instance) {
        return null;
    }
}
