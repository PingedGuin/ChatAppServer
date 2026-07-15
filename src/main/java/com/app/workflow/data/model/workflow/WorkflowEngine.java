package com.app.workflow.data.model.workflow;

import com.app.workflow.data.model.step.StepDefinition;
import com.app.workflow.data.model.step.StepRegistry;
import com.app.workflow.data.model.step.StepResult;
import com.app.workflow.data.model.step.StepStatus;
import com.app.workflow.data.model.workflow.context.WorkflowContext;
import com.app.workflow.data.model.workflow.error.WorkflowError;
import com.app.workflow.data.model.workflow.error.WorkflowErrorCode;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@Slf4j
public class WorkflowEngine {

    private final StepRegistry registry;

    public WorkflowEngine(StepRegistry registry) {
        this.registry = registry;
    }

    public WorkflowResult execute(
            WorkflowDefinition definition,
            WorkflowInstance instance,
            WorkflowContext context) {

        List<StepDefinition> steps = definition.getSteps();

        for (int i = instance.getCurrentStep(); i < steps.size(); i++) {

            StepDefinition currentStep = steps.get(i);

            log.info("Executing step: {}", currentStep.getStepName());

            WorkflowStep step = registry.get(currentStep.getStepName());

            if (step == null) {
                instance.fail(i);

                return new WorkflowResult(WorkflowStatus.FAILED, context,
                        new WorkflowError(WorkflowErrorCode.STEP_NOT_FOUND,
                                "Step not Found", currentStep.getStepName()
                        )
                );
            }
            StepResult result;

            try {
                result = step.execute(definition, context, instance);

            } catch (Exception e) {

                log.error("Step {} failed", currentStep.getStepName(), e);
                instance.fail(i);
                return new WorkflowResult(
                        WorkflowStatus.FAILED,
                        context,
                        new WorkflowError(
                                WorkflowErrorCode.STEP_FAILED,
                                e.getMessage(),
                                currentStep.getStepName()
                        )
                );
            }

            if (result.getStatus() == StepStatus.FAILED) {
                //   instance.setRetryCount(instance.getRetryCount() + 1); // todo continue this :3
                instance.fail(i);

                return new WorkflowResult(
                        WorkflowStatus.FAILED,
                        context,
                        result.getError()
                );
            }

            instance.setCurrentStep(i + 1);
        }
        instance.setStatus(WorkflowStatus.COMPLETED);
        return new WorkflowResult(WorkflowStatus.COMPLETED, context, null);
    }

}