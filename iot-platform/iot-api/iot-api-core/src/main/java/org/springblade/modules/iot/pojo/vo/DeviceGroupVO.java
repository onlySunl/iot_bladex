package org.springblade.modules.iot.pojo.vo;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;
import lombok.EqualsAndHashCode;
import org.springblade.modules.iot.pojo.entity.DeviceGroup;

/**
 * IoT设备分组视图类
 */
@Data
@EqualsAndHashCode(callSuper = true)
@Schema(description = "IoT设备分组VO")
public class DeviceGroupVO extends DeviceGroup {

	@Schema(description = "父分组名称")
	private String parentName;

	@Schema(description = "设备数量")
	private Integer deviceCount;
}
