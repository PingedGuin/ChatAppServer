package com.app.workflow.data.model.step;

import lombok.AllArgsConstructor;
import lombok.Data;

@AllArgsConstructor
@Data
public class StepDefinition {
    private String stepName;
    private Integer stepOrder;
}
