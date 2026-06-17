package com.app.workflow.data.model;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

@Component
@Slf4j
public class WorkflowEngine {

    private final StepRegistry registry;

    public WorkflowEngine(StepRegistry registry) {
        this.registry = registry;
    }

    public void execute(WorkflowDefinition definition, WorkflowContext context) {

        for (String stepName : definition.getSteps()) {
            log.info("Executing step: {}", stepName);
            WorkflowStep step = registry.get(stepName);

            if (step == null) {
                System.out.println("Step not found: " + stepName);
                break;
            }

            StepResult result = step.execute(definition, context);

            if (result.getStatus() == StepStatus.STOPPED) {
                log.info("Workflow stopped at step: {}", stepName);
                break;
            }
        }
        log.info("Workflow completed");
    }
}