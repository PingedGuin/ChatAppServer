package workflow.data.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.Instant;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class StepExecution {
    private String stepName;
    private StepStatus status;
    private Instant startedAt;
    private Instant finishedAt;
}
