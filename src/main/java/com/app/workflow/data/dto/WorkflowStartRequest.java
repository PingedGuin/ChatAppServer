package com.app.workflow.data.dto;

import com.app.workflow.data.WorkflowData;
import com.app.workflow.step.StepName;
import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class WorkflowStartRequest<T extends WorkflowData> {
    private final StepName workflowName;
    private final T data;
}
