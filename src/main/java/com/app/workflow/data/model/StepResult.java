package com.app.workflow.data.model;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class StepResult {
    private final StepStatus status;
    private final String message;

    public static StepResult success() {
        return new StepResult(StepStatus.SUCCESS, null);
    }

    public static StepResult failure(String message) {
        return new StepResult(StepStatus.FAILED, message);
    }
}