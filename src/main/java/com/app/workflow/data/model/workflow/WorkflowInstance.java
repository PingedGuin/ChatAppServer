package com.app.workflow.data.model.workflow;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

import java.util.UUID;


/**
 * Represents an individual instance of a workflow, tracking its execution state,
 * progress, and retry information. Each workflow instance is associated with a
 * specific workflow definition and undergoes a series of steps as dictated by
 * the definition.
 *
 * Fields:
 * - `id`: A unique identifier for the workflow instance.
 * - `workflowId`: The identifier of the workflow definition this instance is based on.
 * - `status`: The current status of the workflow instance, which can be one of
 *   the states defined in the {@code WorkflowStatus} enum (e.g., PENDING, RUNNING, COMPLETED, FAILED).
 * - `currentStep`: The index of the current step being executed in the workflow.
 * - `retryCount`: The number of times the workflow instance has retried execution
 *   after encountering failures.
 *
 * Behavior:
 * - `fail(stepIndex)`: Marks the workflow instance as failed and sets the current step
 *   to the specified index where the failure occurred.
 * - `complete()`: Updates the status of the workflow instance to indicate that it has been completed successfully.
 * - `moveToNextStep()`: Advances the workflow instance to the next step in the defined sequence.
 * - `retry()`: Increments the retry counter to track the number of retries performed
 *   for the workflow execution.
 */
@Data
@Builder
@AllArgsConstructor
public class WorkflowInstance {

    private UUID id;
    private Long workflowId;
    private WorkflowStatus status;
    private int currentStep;
    private int retryCount;

    public void fail(int stepIndex) {
        this.status = WorkflowStatus.FAILED;
        this.currentStep = stepIndex;
    }

    public void complete() {
        this.status = WorkflowStatus.COMPLETED;
    }

    public void moveToNextStep() {
        this.currentStep++;
    }

    public void retry() {
        this.retryCount++;
    }
}