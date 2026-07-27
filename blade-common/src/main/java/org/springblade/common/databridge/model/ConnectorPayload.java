package org.springblade.common.databridge.model;
import lombok.Data;
import java.util.Map;
@Data
public class ConnectorPayload {
    private String topic;
    private Map<String, Object> data;
}
