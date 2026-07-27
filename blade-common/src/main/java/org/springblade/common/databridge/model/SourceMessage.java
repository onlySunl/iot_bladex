package org.springblade.common.databridge.model;
import lombok.Data;
import java.util.Map;
@Data
public class SourceMessage {
    private String topic;
    private byte[] payload;
    private Map<String, String> headers;
}
