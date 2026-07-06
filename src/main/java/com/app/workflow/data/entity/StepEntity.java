package com.app.workflow.data.entity;

import com.app.workflow.step.StepName;
import jakarta.persistence.*;
import lombok.Getter;

@Entity
@Table(name = "workflow_steps")
@Getter
public class StepEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(name = "step_name")
    private StepName stepName;
    @Column(name = "step_order")
    private Integer stepOrder;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "workflow_id")
    private WorkflowDefinitionEntity workflow;

}