package com.app.workflow.data.model.step;

import com.app.workflow.step.StepName;
import lombok.AllArgsConstructor;
import lombok.Data;

@AllArgsConstructor
@Data
public class StepDefinition {
    private StepName stepName;
    private Integer stepOrder;
}
