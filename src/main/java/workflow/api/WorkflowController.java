package workflow.api;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import workflow.data.dto.StartWorkflowRequest;
import workflow.service.WorkflowService;

@RestController
@RequestMapping("/workflows")
public class WorkflowController {

    private final WorkflowService workflowService;

    public WorkflowController(WorkflowService workflowService) {
        this.workflowService = workflowService;
    }

    @PostMapping("/start")
    public ResponseEntity<String> start(@RequestBody StartWorkflowRequest request) {

        String instanceId =
                workflowService.startWorkflow(request);

        return ResponseEntity.ok(instanceId);
    }
}