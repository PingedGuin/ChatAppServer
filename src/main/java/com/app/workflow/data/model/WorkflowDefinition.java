package com.app.workflow.data.model;

import lombok.Getter;

import java.util.List;
import java.util.UUID;

/**
 * Represents the definition of a workflow, including its unique identifier, name,
 * and the sequence of steps that constitute the workflow.
 * <p>
 * A workflow definition serves as a blueprint for workflow instances, detailing
 * the steps to be executed and their order. Each step in the workflow is an
 * implementation of the {@code WorkflowStep} interface, which defines the logic
 * for executing that step.
 * <p>
 * Fields:
 * - `id`: A unique identifier for the workflow definition.
 * - `name`: The name of the workflow, serving as a descriptive label.
 * - `steps`: A list of {@code WorkflowStep} instances defining the steps of the workflow.
 */
@Getter
public class WorkflowDefinition {
    private final UUID workflowCode;
    private final Long workflowId;
    private final String name;
    private final List<StepDefinition> steps;

    public WorkflowDefinition(UUID workflowCode, Long workflowId, String name, List<StepDefinition> steps) {
        this.workflowCode = workflowCode;
        this.workflowId = workflowId;
        this.name = name;
        this.steps = List.copyOf(steps);
    }
}
