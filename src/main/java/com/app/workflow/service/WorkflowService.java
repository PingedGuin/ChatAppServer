package com.app.workflow.service;

import com.app.policy.PolicyEngine;
import com.app.workflow.data.dto.StartWorkflowRequest;
import com.app.workflow.data.entity.StepEntity;
import com.app.workflow.data.entity.WorkflowDefinitionEntity;
import com.app.workflow.data.model.WorkflowDefinition;
import com.app.workflow.data.model.WorkflowEngine;
import com.app.workflow.repository.WorkflowDefinitionRepository;
import com.github.benmanes.caffeine.cache.Cache;
import com.github.benmanes.caffeine.cache.Caffeine;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class WorkflowService {
    private final PolicyEngine policyEngine;
    private final WorkflowDefinitionRepository workflowDefinitionRepository;
    private final WorkflowEngine workflowEngine;
    private final Cache<String, WorkflowDefinition> workflowDefinitionCache = Caffeine.newBuilder()
            .maximumSize(100_000)
            .expireAfterWrite(10, java.util.concurrent.TimeUnit.MINUTES)
            .build();

    public WorkflowService(PolicyEngine policyEngine, WorkflowDefinitionRepository workflowDefinitionRepository, WorkflowEngine workflowEngine) {
        this.policyEngine = policyEngine;
        this.workflowDefinitionRepository = workflowDefinitionRepository;
        this.workflowEngine = workflowEngine;
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
        if (workflowDefinitionCache.getIfPresent(workflowName) != null)
            return workflowDefinitionCache.getIfPresent(workflowName);

        WorkflowDefinitionEntity definitionEntity = workflowDefinitionRepository.findByName(workflowName).orElseThrow(
                () -> new RuntimeException("Workflow not found: " + workflowName));

        var definition = toDefinition(definitionEntity);
        workflowDefinitionCache.put(workflowName,definition);
        return definition;
    }

    private WorkflowDefinition toDefinition(WorkflowDefinitionEntity entity) {
        List<String> stepNames =
                entity.getSteps()
                        .stream()
                        .map(StepEntity::getStepName)
                        .toList();
        return new WorkflowDefinition(entity.getName(),stepNames);
    }
}
