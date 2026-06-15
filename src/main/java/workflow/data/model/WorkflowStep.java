package workflow.data.model;

/**
 * Represents a single step in the workflow execution process. Each step defines a
 * specific unit of work or logic to be executed as part of the overall workflow.
 *
 * Implementations of this interface should provide the concrete logic for the step
 * by overriding the {@code execute()} method. The {@code execute()} method receives
 * a {@code WorkflowContext} that allows the step to access or modify workflow data
 * shared across all steps.
 *
 * The outcome of the step's execution is returned as a {@code StepResult}. This result
 * indicates whether the step succeeded or failed, and optionally includes a message
 * providing additional details about the execution.
 */
public interface WorkflowStep {
    StepResult execute(
            WorkflowContext context
    );
}
