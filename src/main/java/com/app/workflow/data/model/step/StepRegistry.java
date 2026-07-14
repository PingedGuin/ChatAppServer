package com.app.workflow.data.model.step;

import com.app.workflow.data.model.workflow.WorkflowStep;
import com.app.workflow.step.StepName;
import lombok.extern.slf4j.Slf4j;
import org.reflections.Reflections;
import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Component;
import com.app.workflow.annotation.Step;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

@Component
@Slf4j
public class StepRegistry {

    private final Map<StepName, WorkflowStep> steps = new HashMap<>();

    public StepRegistry(List<WorkflowStep> stepBeans) {

        for (WorkflowStep step : stepBeans) {


            Step annotation = step.getClass().getAnnotation(Step.class);

            if (annotation == null) {
                throw new IllegalStateException(
                        "Missing @Step on class: " + step.getClass().getName()
                );
            }
            steps.put(annotation.name(), step);

            log.info("Loaded step: {}", step.getClass().getSimpleName());
        }
    }

    public WorkflowStep get(StepName name) {
        return steps.get(name);
    }
}