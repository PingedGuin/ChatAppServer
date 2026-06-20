package com.app.workflow.data.entity;

import jakarta.persistence.*;
import lombok.Getter;
import java.util.List;
import java.util.UUID;

@Entity
@Table(name = "workflow_definitions")
@Getter
public class WorkflowDefinitionEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "name",nullable = false)
    private String name;

    @Column(name = "description",nullable = false)
    private String description;

    @Column(unique = true, nullable = false, updatable = false)
    private UUID workflowCode;

    @OneToMany(
            mappedBy = "workflow",
            fetch = FetchType.LAZY,
            cascade = CascadeType.ALL,
            orphanRemoval = true
    )
    @OrderBy("stepOrder ASC")
    private List<StepEntity> steps;

    @PrePersist
    public void init() {
        if (workflowCode == null) {
            workflowCode = UUID.randomUUID();
        }
    }
}
