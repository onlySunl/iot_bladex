package org.springblade.modules.iot.haikangisup.xml;

import lombok.Data;
import jakarta.xml.bind.annotation.*;

/**
 * 设备基本信息
 */
@Data
@XmlAccessorType(XmlAccessType.FIELD)
public class DeviceInfo {
	private String deviceID;
	private String deviceName;
	private String model;
	private String firmwareVersion;
	private String hardwareVersion;
}
