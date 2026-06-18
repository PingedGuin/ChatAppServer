package com.app.workflow.data.entity;

import jakarta.persistence.*;

import java.util.List;

@Entity
@Table(name = "workflow_definitions")
public class WorkflowDefinitionEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(name = "name",nullable = false)
    private String name;

    @OneToMany(mappedBy = "workflow")
    private List<StepEntity> steps;
}