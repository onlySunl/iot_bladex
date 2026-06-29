package org.springblade.modules.iot.haikangisup.xml;

import jakarta.xml.bind.annotation.*;
import lombok.Data;

import java.util.List;

@Data
@XmlRootElement(name = "DeviceStatus")
@XmlAccessorType(XmlAccessType.FIELD)
public class DeviceStatus {

    @XmlAttribute
    private String version;

    private String currentDeviceTime;     // 当前设备时间 (ISO 8601)
    private Integer deviceUpTime;         // 运行时间（秒）

    @XmlElementWrapper(name = "CPUList")
    @XmlElement(name = "CPU")
    private List<CPU> cpuList;

    @XmlElementWrapper(name = "MemoryList")
    @XmlElement(name = "Memory")
    private List<Memory> memoryList;

    @XmlElementWrapper(name = "NetPortStatusList")
    @XmlElement(name = "NetPortStatus")
    private List<NetPortStatus> netPortStatusList;
}
