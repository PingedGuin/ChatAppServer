package com.app.workflow.data.entity;

import jakarta.persistence.*;
import lombok.Data;

@Entity
@Table(name = "workflow_steps")
@Data
public class StepEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(name = "step_name")
    private String stepName;
    @Column(name = "step_order")
    private Integer stepOrder;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "workflow_id")
    private WorkflowDefinitionEntity workflow;
}