package com.app.workflow.data.model.workflow;

import java.util.HashMap;
import java.util.Map;

/**
 * Represents the context for a workflow execution, carrying data that can be shared
 * across various steps of the workflow. The context acts as a central storage for
 * the state and information required during the execution of a workflow.
 *
 * This class is typically used as a parameter in the {@code execute()} method of
 * {@code WorkflowStep} implementations, allowing each step to access and modify
 * the workflow's state.
 *
 * The context uses a key-value data structure to store information, where keys are
 * strings and values are generic objects. It enables flexible data sharing while
 * maintaining loose coupling between workflow steps.
 */
public class WorkflowContext {
    private final Map<String, Object> data;

    public WorkflowContext(){
        this.data = new HashMap<>();
    }

    public void put(String key, Object value) {
        data.put(key, value);
    }

    public <T> T get(String key, Class<T> type) {
        return type.cast(data.get(key));
    }
}
