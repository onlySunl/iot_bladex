package org.springblade.modules.nvr.pojo.vo;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;
import lombok.EqualsAndHashCode;
import org.springblade.modules.nvr.pojo.entity.Device;

/**
 * IoT设备视图类
 */
@Data
@EqualsAndHashCode(callSuper = true)
@Schema(description = "IoT设备VO")
public class DeviceVO extends Device {

	@Schema(description = "在线状态名")
	private String stateName;

	@Schema(description = "产品名称")
	private String productName;
}
