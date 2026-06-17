package workflow.data.model;

public class WorkflowEngine {

    private final StepRegistry registry;

    public WorkflowEngine(StepRegistry registry) {
        this.registry = registry;
    }

    public void execute(WorkflowDefinition definition, WorkflowContext context) {

        for (String stepName : definition.getSteps()) {

            System.out.println("Executing: " + stepName);

            WorkflowStep step = registry.get(stepName);

            if (step == null) {
                System.out.println("Step not found: " + stepName);
                break;
            }

            StepResult result = step.execute(definition, context);

            if (!result.isSuccess()) {
                System.out.println("Workflow stopped at: " + stepName);
                break;
            }
        }

        System.out.println("Workflow finished");
    }
}