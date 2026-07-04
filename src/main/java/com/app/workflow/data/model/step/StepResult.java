package com.app.workflow.data.model.step;

import com.app.workflow.data.model.workflow.WorkflowError;
import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class StepResult {

    private final StepStatus status;
    private final WorkflowError error;

    public static StepResult success() {
        return new StepResult(StepStatus.SUCCESS, null);
    }

    public static StepResult failure(WorkflowError error) {
        return new StepResult(StepStatus.FAILED, error);
    }
}