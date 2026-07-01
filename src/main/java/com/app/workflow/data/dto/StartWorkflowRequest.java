package com.app.workflow.data.dto;

import com.app.workflow.step.StepName;
import lombok.Getter;

@Getter
public class StartWorkflowRequest<T> {

    private final StepName workflowName;
    private final T data;

    private StartWorkflowRequest(Builder<T> builder) {
        this.workflowName = builder.workflowName;
        this.data = builder.data;
    }

    public static <T> Builder<T> builder() {
        return new Builder<>();
    }

    public static class Builder<T> {

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

        public StartWorkflowRequest<T> build() {
            return new StartWorkflowRequest<>(this);
        }
    }
}