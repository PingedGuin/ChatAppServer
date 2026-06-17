package com.app.workflow.data.model;

import lombok.Data;

@Data
public class StepResult {

    private final boolean success;
    private final String message;

    private StepResult(boolean success, String message) {
        this.success = success;
        this.message = message;
    }

    public static StepResult success() {
        return new StepResult(true, null);
    }

    public static StepResult failure(String message) {
        return new StepResult(false, message);
    }
}