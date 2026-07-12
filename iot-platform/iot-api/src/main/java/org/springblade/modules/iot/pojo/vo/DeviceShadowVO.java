package org.springblade.modules.iot.pojo.vo;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;
import lombok.EqualsAndHashCode;
import org.springblade.modules.iot.pojo.entity.DeviceShadow;

@Data
@EqualsAndHashCode(callSuper = true)
@Schema(description = "DeviceShadowVO")
public class DeviceShadowVO extends DeviceShadow {
	private static final long serialVersionUID = 1L;
}
