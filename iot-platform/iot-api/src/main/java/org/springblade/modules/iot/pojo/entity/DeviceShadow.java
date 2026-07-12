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
 * 设备影子 实体
 *
 * @author pmc
 */
@Data
@TableName("iot_device_shadow")
@EqualsAndHashCode(callSuper = true)
@Schema(description = "DeviceShadow对象")
public class DeviceShadow extends CustomBaseEntity {

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
	 * 设备状态（JSON）
	 */
	@TableField(value = "state")
	@AutoColumn(comment = "设备状态", defaultValueType = DefaultValueEnum.NULL)
	private String state;

	/**
	 * 元数据（JSON）
	 */
	@TableField(value = "metadata")
	@AutoColumn(comment = "元数据", defaultValueType = DefaultValueEnum.NULL)
	private String metadata;

	/**
	 * 最后更新时间
	 */
	@TableField(value = "last_update_time")
	@AutoColumn(comment = "最后更新时间", defaultValueType = DefaultValueEnum.NULL)
	private Long lastUpdateTime;

}
