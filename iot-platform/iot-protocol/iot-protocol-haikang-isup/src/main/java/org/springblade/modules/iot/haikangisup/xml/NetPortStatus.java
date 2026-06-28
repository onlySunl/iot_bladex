package org.springblade.modules.iot.haikangisup.xml;

import lombok.Data;
import jakarta.xml.bind.annotation.*;

/**
 * 网络端口状态信息
 */
@Data
@XmlAccessorType(XmlAccessType.FIELD)
public class NetPortStatus {
	private String portNo;
	private String status;
}
