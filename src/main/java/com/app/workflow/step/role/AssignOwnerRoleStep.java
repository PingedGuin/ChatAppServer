package com.app.workflow.step.role;

import com.app.workflow.annotation.Step;
import com.app.workflow.data.model.step.StepResult;
import com.app.workflow.data.model.workflow.context.WorkflowContext;
import com.app.workflow.data.model.workflow.WorkflowDefinition;
import com.app.workflow.data.model.workflow.WorkflowInstance;
import com.app.workflow.data.model.workflow.WorkflowStep;
import com.app.workflow.step.StepName;
import org.springframework.stereotype.Component;

@Component
@Step(name = StepName.ASSIGN_OWNER_ROLE)
public class AssignOwnerRoleStep implements WorkflowStep {
    @Override
    public StepResult execute(WorkflowDefinition definition, WorkflowContext context, WorkflowInstance instance) {
        return null;
    }
}
