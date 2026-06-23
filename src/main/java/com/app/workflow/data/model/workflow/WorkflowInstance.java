package com.app.workflow.data.model.workflow;

import lombok.Data;

import java.util.UUID;

/**
 * Represents an instance of a workflow, tracking its state and progress as it
 * executes through its defined steps. A workflow instance is created based on
 * a workflow definition and provides information about the current execution state.
 * <p>
 * Fields:
 * - `id`: A unique identifier for the workflow instance.
 * - `workflowId`: The identifier of the associated workflow definition.
 * - `status`: The current status of the workflow instance. Possible statuses are
 * defined in the {@link WorkflowStatus} enumeration.
 * - `currentStep`: The index of the step currently being executed, starting from zero.
 * This field is updated as the workflow progresses through its steps.
 */
@Data
public class WorkflowInstance {
    private UUID id;
    private Long workflowId;
    private WorkflowStatus status;
    private int currentStep;

    private WorkflowInstance(Builder builder) {
        this.id = builder.id;
        this.workflowId = builder.workflowId;
        this.status = builder.status;
        this.currentStep = builder.currentStep;
    }

    public static Builder builder() {
        return new Builder();
    }

    public static class Builder {
        private UUID id;
        private Long workflowId;
        private WorkflowStatus status;
        private int currentStep;

        public Builder id(UUID id) {
            this.id = id;
            return this;
        }

        public Builder workflowId(Long workflowId) {
            this.workflowId = workflowId;
            return this;
        }

        public Builder status(WorkflowStatus status) {
            this.status = status;
            return this;
        }

        public Builder currentStep(int currentStep) {
            this.currentStep = currentStep;
            return this;
        }

        public WorkflowInstance build() {
            return new WorkflowInstance(this);
        }
    }
}