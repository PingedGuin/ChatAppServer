package com.app.workflow.data.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.Instant;

/**
 * Represents the execution details of a single step within the workflow process.
 * This class is used to track the state and timing information related to the execution
 * of a workflow step.
 *
 * Fields:
 * - `stepName`: The name of the step being executed. Typically corresponds to the step's
 *   definition within the workflow.
 * - `status`: The current status of the step's execution. The possible values are defined in
 *   the {@code StepStatus} enumeration, such as SUCCESS, FAILED, SKIPPED, etc.
 * - `startedAt`: The timestamp indicating when the step execution started.
 * - `finishedAt`: The timestamp indicating when the step execution finished.
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class StepExecution {
    private String stepName;
    private StepStatus status;
    private Instant startedAt;
    private Instant finishedAt;
}
