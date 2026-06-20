package com.app.workflow.data.model;

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

    public void execute(
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
                return;
            }

            StepResult result = step.execute(definition, context);

            if (result.getStatus() == StepStatus.FAILED) {
                instance.setCurrentStep(i);
                instance.setStatus(WorkflowStatus.FAILED);
                return;
            }

            instance.setCurrentStep(i + 1);
        }

        instance.setStatus(WorkflowStatus.COMPLETED);
        log.info("Workflow completed");
    }

}