package org.springblade.modules.iot.haikangisup.xml;

import jakarta.xml.bind.annotation.XmlAccessType;
import jakarta.xml.bind.annotation.XmlAccessorType;
import lombok.Data;

@Data
@XmlAccessorType(XmlAccessType.FIELD)
public class Memory {
    private String memoryDescription;
    private Float memoryUsage;      // 已用内存 (MB)
    private Float memoryAvailable;  // 可用内存 (MB)
}
