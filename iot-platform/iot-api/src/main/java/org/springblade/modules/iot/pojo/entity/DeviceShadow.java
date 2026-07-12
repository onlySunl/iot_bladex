package org.springblade.modules.iot.pojo.entity;

import com.baomidou.mybatisplus.annotation.TableName;
import com.tangzc.autotable.annotation.Index;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;
import lombok.EqualsAndHashCode;
import org.springblade.modules.iot.common.entity.CustomBaseEntity;

/**
 * 设备影子 实体
 *
 * @author pmc
 */
@Data
@TableName("iot_device_shadow")
@EqualsAndHashCode(callSuper = true)
@Schema(description = "DeviceShadow对象")
public class DeviceShadow extends CustomBaseEntity {

	private static final long serialVersionUID = 1L;

	@Schema(description = "设备ID")
	@Index
	private Long deviceId;

	@Schema(description = "设备状态（JSON）")
	private String state;

	@Schema(description = "元数据（JSON）")
	private String metadata;

	@Schema(description = "最后更新时间")
	private Long lastUpdateTime;

}
