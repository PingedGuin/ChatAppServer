package workflow.service;

import com.app.policy.PolicyEngine;
import workflow.data.dto.StartWorkflowRequest;

public class WorkflowService {
    private final PolicyEngine policyEngine;

    public WorkflowService(PolicyEngine policyEngine) {
        this.policyEngine = policyEngine;
    }

    public String startWorkflow(StartWorkflowRequest request) {
        // todo ad policy engine here before starting workflow
        // policyEngine.check();


        // TODO: load workflow definition

        // TODO: create workflow instance

        // TODO: execute workflow

        return "Workflow started";
    }


}
