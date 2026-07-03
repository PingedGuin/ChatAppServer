package com.app.workflow.data.model.workflow;

import com.app.workflow.data.model.step.StepDefinition;
import com.app.workflow.data.model.step.StepRegistry;
import com.app.workflow.data.model.step.StepResult;
import com.app.workflow.data.model.step.StepStatus;
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
                log.error("Step not found: {}", currentStep.getStepName());
                instance.setStatus(WorkflowStatus.FAILED);
                return null;
            }

            StepResult result = step.execute(definition, context,instance);

            if (result.getStatus() == StepStatus.FAILED) {
                instance.setCurrentStep(i);
                instance.setStatus(WorkflowStatus.FAILED);
                return null;
            }

            instance.setCurrentStep(i + 1);
        }

        instance.setStatus(WorkflowStatus.COMPLETED);
        log.info("Workflow completed");
    }

}