package com.app.workflow.data;

public interface WorkflowData {
    default boolean isValid() {
        throw new UnsupportedOperationException("isValid method must be implemented");
    }
}
