package org.springblade.modules.iot.haikangisup.xml;

import lombok.Data;
import jakarta.xml.bind.annotation.*;

/**
 * CPU使用率信息
 */
@Data
@XmlAccessorType(XmlAccessType.FIELD)
public class CPU {
	private Integer cpuUtilization;
}
