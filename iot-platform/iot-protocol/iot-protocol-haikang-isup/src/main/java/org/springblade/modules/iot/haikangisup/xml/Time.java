package org.springblade.modules.iot.haikangisup.xml;

import lombok.Data;
import jakarta.xml.bind.annotation.*;

/**
 * 设备时间信息
 */
@Data
@XmlAccessorType(XmlAccessType.FIELD)
public class Time {
	private String dateTime;
	private String timeZone;
}
