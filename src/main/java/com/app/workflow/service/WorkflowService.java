package com.app.workflow.service;

import com.app.policy.PolicyEngine;
import com.app.workflow.data.dto.StartWorkflowRequest;
import com.app.workflow.data.model.WorkflowDefinition;
import com.app.workflow.repository.WorkflowDefinitionRepository;
import com.github.benmanes.caffeine.cache.Cache;
import com.github.benmanes.caffeine.cache.Caffeine;
import org.springframework.stereotype.Service;

@Service
public class WorkflowService {
    private final PolicyEngine policyEngine;
    private final WorkflowDefinitionRepository workflowDefinitionRepository;
    private final Cache<String, WorkflowDefinition> workflowDefinitionCache = Caffeine.newBuilder()
            .maximumSize(100_000)
            .expireAfterWrite(10, java.util.concurrent.TimeUnit.MINUTES)
            .build();

    public WorkflowService(PolicyEngine policyEngine, WorkflowDefinitionRepository workflowDefinitionRepository) {
        this.policyEngine = policyEngine;
        this.workflowDefinitionRepository = workflowDefinitionRepository;
    }

    public String startWorkflow(StartWorkflowRequest request) {
        // todo ad policy engine here before starting workflow
        // policyEngine.check();


        // TODO: load workflow definition

        // TODO: create workflow instance

        // TODO: execute workflow

        return "Workflow started";
    }
    public WorkflowDefinition loadWorkflow(String workflowName) {
        // database
        // mapping entity -> definition
        return null;
    }


}
