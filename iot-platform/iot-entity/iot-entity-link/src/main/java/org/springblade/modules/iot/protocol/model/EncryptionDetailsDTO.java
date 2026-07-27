package org.springblade.modules.iot.protocol.model;

import lombok.Data;

/**
 * Encryption Details DTO
 * Compatibility class for thinglinks migration
 */
@Data
public class EncryptionDetailsDTO {
    private String encryptionType;
    private String secretKey;
    private String iv;
}
