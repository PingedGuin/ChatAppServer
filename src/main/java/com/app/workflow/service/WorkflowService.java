package com.app.workflow.service;

import com.app.policy.PolicyEngine;
import com.app.workflow.data.dto.StartWorkflowRequest;
import com.app.workflow.data.model.WorkflowDefinition;
import com.app.workflow.repository.WorkflowDefinitionRepository;
import org.springframework.stereotype.Service;

@Service
public class WorkflowService {
    private final PolicyEngine policyEngine;
    private final WorkflowDefinitionRepository workflowDefinitionRepository;

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
