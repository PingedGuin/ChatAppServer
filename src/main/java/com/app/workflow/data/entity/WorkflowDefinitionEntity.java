package com.app.workflow.data.entity;

import jakarta.persistence.*;
import lombok.Data;

import java.util.List;

@Entity
@Table(name = "workflow_definitions")
@Data
public class WorkflowDefinitionEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(name = "name",nullable = false)
    private String name;

    @OneToMany(
            mappedBy = "workflow",
            fetch = FetchType.LAZY
    )
    @OrderBy("stepOrder ASC")
    private List<StepEntity> steps;
}
