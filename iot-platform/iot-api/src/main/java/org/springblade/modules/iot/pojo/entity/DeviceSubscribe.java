package org.springblade.modules.iot.pojo.entity;

import com.baomidou.mybatisplus.annotation.TableName;
import com.tangzc.autotable.annotation.Index;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;
import lombok.EqualsAndHashCode;
import org.springblade.modules.iot.common.entity.CustomBaseEntity;

/**
 * 设备订阅 实体
 *
 * @author pmc
 */
@Data
@TableName("iot_device_subscribe")
@EqualsAndHashCode(callSuper = true)
@Schema(description = "DeviceSubscribe对象")
public class DeviceSubscribe extends CustomBaseEntity {

	private static final long serialVersionUID = 1L;

	@Schema(description = "设备ID")
	@Index
	private Long deviceId;

	@Schema(description = "订阅类型: PRODUCT, DEVICE, PART")
	private String type;

	@Schema(description = "主题ID")
	private String topicId;

}
