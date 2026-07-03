package com.app.workflow.data.model.workflow;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class WorkflowResult<T> {
    private WorkflowStatus status;
    private WorkflowContext context;
    private final WorkflowError error;
}