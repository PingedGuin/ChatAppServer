package com.app.workflow.data.dto;

import com.app.workflow.data.WorkflowData;
import com.app.workflow.step.StepName;
import lombok.Getter;

@Getter
public class WorkflowStartRequest<T extends WorkflowData> {

    private final StepName workflowName;
    private final T data;

    private WorkflowStartRequest(Builder<T> builder) {
        this.workflowName = builder.workflowName;
        this.data = builder.data;
    }

    public static <T extends WorkflowData> Builder<T> builder() {
        return new Builder<>();
    }

    public static class Builder<T extends WorkflowData> {

        private StepName workflowName;
        private T data;

        public Builder<T> workflowName(StepName workflowName) {
            this.workflowName = workflowName;
            return this;
        }

        public Builder<T> data(T data) {
            this.data = data;
            return this;
        }

        public WorkflowStartRequest<T> build() {
            if (workflowName == null)
                throw new IllegalStateException("workflowName is required");

            if (data == null)
                throw new IllegalStateException("data is required");

            return new WorkflowStartRequest<>(this);
        }
    }
}
