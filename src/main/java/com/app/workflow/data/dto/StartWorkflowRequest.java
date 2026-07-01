package com.app.workflow.data.dto;

import com.app.workflow.step.StepName;
import lombok.Getter;

import java.util.Map;
@Getter
public class StartWorkflowRequest {
    private final StepName workflowName;
    private final Map<String, Object> data;

    private StartWorkflowRequest(Builder builder) {
        this.workflowName = builder.workflowName;
        this.data = builder.data;
    }

    public static Builder builder() {
        return new Builder();
    }

    public static class Builder {
        private StepName workflowName;
        private Map<String, Object> data;

        public Builder workflowName(StepName workflowName) {
            this.workflowName = workflowName;
            return this;
        }
        public Builder data(Map<String, Object> data) {
            this.data = data;
            return this;
        }

        public StartWorkflowRequest build() {
            return new StartWorkflowRequest(this);
        }
    }
}
