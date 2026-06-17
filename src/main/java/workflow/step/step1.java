package workflow.step;

import workflow.data.model.StepResult;
import workflow.data.model.WorkflowContext;
import workflow.data.model.WorkflowDefinition;
import workflow.data.model.WorkflowStep;
import workflow.step.annotation.Step;

@Step(stepName = "step1")
public class step1 implements WorkflowStep {
    @Override
    public StepResult execute(WorkflowDefinition definition, WorkflowContext context) {
        System.out.println("Step 1 executed");
        return StepResult.success();
    }
}
