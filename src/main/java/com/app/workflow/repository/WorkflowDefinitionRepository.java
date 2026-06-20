package com.app.workflow.repository;

import com.app.workflow.data.entity.WorkflowDefinitionEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface WorkflowDefinitionRepository extends JpaRepository <WorkflowDefinitionEntity,Long>{
    Optional<WorkflowDefinitionEntity> findByName(String name);
}
