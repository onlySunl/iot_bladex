package org.springblade.modules.iot.haikangisup.xml;

import jakarta.xml.bind.annotation.XmlAccessType;
import jakarta.xml.bind.annotation.XmlAccessorType;
import lombok.Data;

@Data
@XmlAccessorType(XmlAccessType.FIELD)
public class CPU {
    private Integer cpuUtilization; // CPU使用率 (0~100)
}
