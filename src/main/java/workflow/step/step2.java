package workflow.step;

import workflow.data.model.StepResult;
import workflow.data.model.WorkflowContext;
import workflow.data.model.WorkflowDefinition;
import workflow.data.model.WorkflowStep;
import workflow.step.annotation.Step;

@Step(stepName = "step2")
public class step2 implements WorkflowStep {
    @Override
    public StepResult execute(WorkflowDefinition definition, WorkflowContext context) {
        System.out.println("Step 2 executed");
        return StepResult.success();
    }
}
