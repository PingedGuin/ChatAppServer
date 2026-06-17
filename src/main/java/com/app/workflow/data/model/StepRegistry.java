package com.app.workflow.data.model;

import lombok.extern.slf4j.Slf4j;
import org.reflections.Reflections;
import org.springframework.stereotype.Component;
import com.app.workflow.annotation.Step;

import java.util.HashMap;
import java.util.Map;
import java.util.Set;

@Component
@Slf4j
public class StepRegistry {
    private final Map<String, WorkflowStep> steps = new HashMap<>();

    public StepRegistry() {
        loadSteps();
    }

    private void loadSteps() {

        Reflections reflections =
                new Reflections("com.app.workflow.step");

        Set<Class<?>> stepClasses =
                reflections.getTypesAnnotatedWith(Step.class);

        for (Class<?> clazz : stepClasses) {
            if (!WorkflowStep.class.isAssignableFrom(clazz)) {
                log.error("Invalid step: {}", clazz.getName());
                continue;
            }
            try {
                Step annotation =
                        clazz.getAnnotation(Step.class);

                WorkflowStep instance =
                        (WorkflowStep) clazz
                                .getDeclaredConstructor()
                                .newInstance();

                steps.put(annotation.stepName(), instance);
                log.info("Loaded step: {}", clazz.getName());

            } catch (Exception e) {
                log.error("Failed to load step: {}",
                        clazz.getName(), e);
            }
        }
    }

    public void register(String name, WorkflowStep step) {
        steps.put(name, step);
    }

    public WorkflowStep get(String name) {
        return steps.get(name);
    }
}
