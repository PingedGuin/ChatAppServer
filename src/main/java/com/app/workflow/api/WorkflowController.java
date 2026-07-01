package com.app.workflow.api;

import com.app.guild.service.GuildApplicationService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.app.workflow.data.dto.StartWorkflowRequest;
import com.app.workflow.service.WorkflowService;

@RestController
@RequestMapping("/workflows")
public class WorkflowController {
    GuildApplicationService guildApplicationService;

    public WorkflowController(GuildApplicationService guildApplicationService) {
        this.guildApplicationService = guildApplicationService;
    }

    @PostMapping("/start")
    public ResponseEntity<String> start(@RequestBody StartWorkflowRequest request) {

        return ResponseEntity.ok().build();
    }
}