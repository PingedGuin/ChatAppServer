package com.app.workflow.data.model.workflow;

import com.app.workflow.data.model.workflow.context.WorkflowContext;
import com.app.workflow.data.model.workflow.error.WorkflowError;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
@AllArgsConstructor
public class WorkflowResult {
    private WorkflowStatus status;
    private WorkflowContext context;
    private final WorkflowError error;
}