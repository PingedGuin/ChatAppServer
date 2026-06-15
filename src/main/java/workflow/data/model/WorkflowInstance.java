package workflow.data.model;

import java.util.UUID;

/**
 * Represents an instance of a workflow, tracking its state and progress as it
 * executes through its defined steps. A workflow instance is created based on
 * a workflow definition and provides information about the current execution state.
 *
 * Fields:
 * - `id`: A unique identifier for the workflow instance.
 * - `workflowId`: The identifier of the associated workflow definition.
 * - `status`: The current status of the workflow instance. Possible statuses are
 *   defined in the {@link WorkflowStatus} enumeration.
 * - `currentStep`: The index of the step currently being executed, starting from zero.
 *   This field is updated as the workflow progresses through its steps.
 */
public class WorkflowInstance {
    private UUID id;
    private String workflowId; // definition id
    private WorkflowStatus status;
    private int currentStep;
}
