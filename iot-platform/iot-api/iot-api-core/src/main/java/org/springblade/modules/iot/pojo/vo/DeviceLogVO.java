package org.springblade.modules.iot.pojo.vo;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;
import lombok.EqualsAndHashCode;
import org.springblade.modules.iot.pojo.entity.DeviceLog;

@Data
@EqualsAndHashCode(callSuper = true)
@Schema(description = "DeviceLogVO")
public class DeviceLogVO extends DeviceLog {
	private static final long serialVersionUID = 1L;
}
