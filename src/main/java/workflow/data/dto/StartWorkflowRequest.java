package workflow.data.dto;

import lombok.Data;

import java.util.Map;
@Data
public class StartWorkflowRequest {

    private String workflowName;

    private Map<String, Object> data;

}

//public <T> T get(String key, Class<T> type) { //todo need to change map data to this to be type save map
//    return type.cast(values.get(key));
//}