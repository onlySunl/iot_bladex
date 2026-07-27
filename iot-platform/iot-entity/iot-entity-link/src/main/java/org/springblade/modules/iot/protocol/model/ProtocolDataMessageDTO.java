package org.springblade.modules.iot.protocol.model;

import lombok.Data;
import java.util.Map;

/**
 * Protocol Data Message DTO
 * Compatibility class for thinglinks migration
 */
@Data
public class ProtocolDataMessageDTO {
    private String productId;
    private String deviceId;
    private String messageType;
    private Map<String, Object> data;
    private Long timestamp;
    private EncryptionDetailsDTO encryption;
}
