package com.app.workflow.data.model.workflow;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
@AllArgsConstructor
public class WorkflowError {
    private final String code;

    private final String message;

    private final String stepName;

}
