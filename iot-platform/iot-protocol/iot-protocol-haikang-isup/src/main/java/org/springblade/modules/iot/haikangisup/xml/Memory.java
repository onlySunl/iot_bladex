package org.springblade.modules.iot.haikangisup.xml;

import lombok.Data;
import jakarta.xml.bind.annotation.*;

/**
 * 内存信息
 */
@Data
@XmlAccessorType(XmlAccessType.FIELD)
public class Memory {
	private Integer memoryUtilization;
}
