package com.app.workflow.data.model;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.List;

/**
 * Represents the definition of a workflow, including its unique identifier, name,
 * and the sequence of steps that constitute the workflow.
 *
 * A workflow definition serves as a blueprint for workflow instances, detailing
 * the steps to be executed and their order. Each step in the workflow is an
 * implementation of the {@code WorkflowStep} interface, which defines the logic
 * for executing that step.
 *
 * Fields:
 * - `id`: A unique identifier for the workflow definition.
 * - `name`: The name of the workflow, serving as a descriptive label.
 * - `steps`: A list of {@code WorkflowStep} instances defining the steps of the workflow.
 */
@AllArgsConstructor
@Data
public class WorkflowDefinition {
    private String id;
    private String name;
    private final List<String> steps;
}
