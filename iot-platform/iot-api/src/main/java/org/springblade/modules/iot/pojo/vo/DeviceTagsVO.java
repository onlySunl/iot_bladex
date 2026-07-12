package org.springblade.modules.iot.pojo.vo;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;
import lombok.EqualsAndHashCode;
import org.springblade.modules.iot.pojo.entity.DeviceTags;

@Data
@EqualsAndHashCode(callSuper = true)
@Schema(description = "DeviceTagsVO")
public class DeviceTagsVO extends DeviceTags {
	private static final long serialVersionUID = 1L;
}
