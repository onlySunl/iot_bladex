package org.springblade.modules.iot.haikangisup.xml;

import lombok.Data;
import jakarta.xml.bind.annotation.*;

/**
 * 设备状态信息
 */
@Data
@XmlAccessorType(XmlAccessType.FIELD)
public class DeviceStatus {
	private String deviceID;
	private Integer online;
	private Integer signalStrength;
}
