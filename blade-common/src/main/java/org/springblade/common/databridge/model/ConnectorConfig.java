package org.springblade.common.databridge.model;
import lombok.Data;
@Data
public class ConnectorConfig {
    private String id;
    private String name;
    private ConnectorType type;
    private String url;
}
