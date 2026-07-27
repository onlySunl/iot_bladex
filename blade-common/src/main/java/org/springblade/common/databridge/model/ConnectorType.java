package org.springblade.common.databridge.model;
import lombok.AllArgsConstructor;
import lombok.Getter;
@Getter
@AllArgsConstructor
public enum ConnectorType {
    HTTP, MQTT, KAFKA, TCP, UDP
}
