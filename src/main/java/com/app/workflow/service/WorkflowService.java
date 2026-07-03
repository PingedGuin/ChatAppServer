package com.app.workflow.service;

import com.app.policy.PolicyEngine;
import com.app.workflow.data.WorkflowData;
import com.app.workflow.data.dto.WorkflowStartRequest;
import com.app.workflow.data.entity.WorkflowDefinitionEntity;
import com.app.workflow.data.model.step.StepDefinition;
import com.app.workflow.data.model.workflow.*;
import com.app.workflow.repository.WorkflowDefinitionRepository;
import com.app.workflow.step.StepName;
import com.github.benmanes.caffeine.cache.Cache;
import com.github.benmanes.caffeine.cache.Caffeine;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;
import java.util.concurrent.TimeUnit;

@Service
public class WorkflowService {
    private final PolicyEngine policyEngine;
    private final WorkflowDefinitionRepository workflowDefinitionRepository;
    // private final workflowInstanceRepository workflowInstanceRepository;
    private final WorkflowEngine workflowEngine;
    private final Cache<StepName, WorkflowDefinition> workflowDefinitionCache = Caffeine.newBuilder()
            .maximumSize(100_000)
            .expireAfterWrite(10, TimeUnit.MINUTES)
            .build();

    public WorkflowService(PolicyEngine policyEngine, WorkflowDefinitionRepository workflowDefinitionRepository, WorkflowEngine workflowEngine) {
        this.policyEngine = policyEngine;
        this.workflowDefinitionRepository = workflowDefinitionRepository;
        this.workflowEngine = workflowEngine;
    }

    public <T extends WorkflowData> WorkflowResult startWorkflow(WorkflowStartRequest<T> request) {        // todo ad policy engine here before starting workflow
        // policyEngine.check();
        var workflowDefinition = loadWorkflow(request.getWorkflowName());
        var instance = startWorkflowInstance(workflowDefinition);
        //   workflowInstanceRepository.save(instance); //todo

        return workflowEngine.execute(workflowDefinition, instance, new WorkflowContext());
    }

    public WorkflowDefinition loadWorkflow(StepName workflowName) {
        if (workflowDefinitionCache.getIfPresent(workflowName) != null)
            return workflowDefinitionCache.getIfPresent(workflowName);

        WorkflowDefinitionEntity definitionEntity = workflowDefinitionRepository.findByName(String.valueOf(workflowName)).orElseThrow(
                () -> new RuntimeException("Workflow not found: " + workflowName.name()));

        var definition = toDefinition(definitionEntity);
        workflowDefinitionCache.put(workflowName, definition);
        return definition;
    }

    private WorkflowDefinition toDefinition(WorkflowDefinitionEntity entity) {

        List<StepDefinition> steps =
                entity.getSteps()
                        .stream()
                        .map(step -> new StepDefinition(
                                step.getStepName(),
                                step.getStepOrder()
                        ))
                        .toList();

        return new WorkflowDefinition(
                entity.getWorkflowCode(),
                entity.getId(),
                entity.getName(),
                entity.getVersion()
                ,steps
        );
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
