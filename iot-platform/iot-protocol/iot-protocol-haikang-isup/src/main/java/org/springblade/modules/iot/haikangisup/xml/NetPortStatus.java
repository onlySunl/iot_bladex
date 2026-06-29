package org.springblade.modules.iot.haikangisup.xml;

import jakarta.xml.bind.annotation.XmlAccessType;
import jakarta.xml.bind.annotation.XmlAccessorType;
import lombok.Data;

@Data
@XmlAccessorType(XmlAccessType.FIELD)
public class NetPortStatus {
    private Integer id;
    private String netPortDescription; // ctrl / data1 / data2
    private String linkStatus;         // connected / disconnected
}
