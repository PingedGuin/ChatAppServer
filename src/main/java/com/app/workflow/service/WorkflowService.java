package com.app.workflow.service;

import com.app.policy.PolicyEngine;
import com.app.workflow.data.dto.StartWorkflowRequest;
import com.app.workflow.data.entity.StepEntity;
import com.app.workflow.data.entity.WorkflowDefinitionEntity;
import com.app.workflow.data.model.*;
import com.app.workflow.repository.WorkflowDefinitionRepository;
import com.github.benmanes.caffeine.cache.Cache;
import com.github.benmanes.caffeine.cache.Caffeine;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
public class WorkflowService {
    private final PolicyEngine policyEngine;
    private final WorkflowDefinitionRepository workflowDefinitionRepository;
    // private final workflowInstanceRepository workflowInstanceRepository;
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

        var workflowDefinition = loadWorkflow(request.getWorkflowName());
        var instance = startWorkflowInstance(workflowDefinition);
        //   workflowInstanceRepository.save(instance); //todo save instance

        workflowEngine.execute(workflowDefinition, instance, new WorkflowContext());
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
        return new WorkflowDefinition(entity.getId(), entity.getName(),stepNames);
    }
    private WorkflowInstance startWorkflowInstance(WorkflowDefinition workflowDefinition) {
        return WorkflowInstance.builder()
                        .id(UUID.randomUUID())
                        .workflowId(workflowDefinition.getWorkflowId())
                        .status(WorkflowStatus.RUNNING)
                        .currentStep(0)
                        .build();
    }
}
