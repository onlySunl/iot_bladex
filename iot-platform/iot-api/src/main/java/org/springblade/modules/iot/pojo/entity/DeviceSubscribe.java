package org.springblade.modules.iot.pojo.entity;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableName;
import com.tangzc.autotable.annotation.AutoColumn;
import com.tangzc.autotable.annotation.Index;
import com.tangzc.autotable.annotation.enums.DefaultValueEnum;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;
import lombok.EqualsAndHashCode;
import org.springblade.common.entity.CustomBaseEntity;

import java.io.Serial;

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

	@Serial
	private static final long serialVersionUID = 1L;

	/**
	 * 设备ID
	 */
	@TableField(value = "device_id")
	@AutoColumn(comment = "设备ID", defaultValueType = DefaultValueEnum.NULL)
	@Index
	private Long deviceId;

	/**
	 * 订阅类型: PRODUCT, DEVICE, PART
	 */
	@TableField(value = "type")
	@AutoColumn(comment = "订阅类型", length = 32, defaultValueType = DefaultValueEnum.NULL)
	private String type;

	/**
	 * 主题ID
	 */
	@TableField(value = "topic_id")
	@AutoColumn(comment = "主题ID", length = 128, defaultValueType = DefaultValueEnum.NULL)
	private String topicId;

}
