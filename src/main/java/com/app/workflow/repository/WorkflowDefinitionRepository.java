package com.app.workflow.repository;

import com.app.workflow.data.entity.StepEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface WorkflowDefinitionRepository extends JpaRepository <StepEntity,Long>{

}
