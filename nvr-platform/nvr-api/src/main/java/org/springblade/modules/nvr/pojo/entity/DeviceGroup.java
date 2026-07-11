package org.springblade.modules.nvr.pojo.entity;

import com.baomidou.mybatisplus.annotation.TableName;
import com.tangzc.autotable.annotation.ColumnType;
import com.tangzc.autotable.annotation.enums.ColumnTypeEnum;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;
import lombok.EqualsAndHashCode;
import org.springblade.modules.nvr.common.entity.CustomBaseEntity;

import java.io.Serial;

/**
 * IoT设备分组实体类
 * 迁移自 NexIoT - IoTDeviceGroup
 */
@Data
@EqualsAndHashCode(callSuper = true)
@TableName("iot_device_group")
@Schema(description = "IoT设备分组实体类")
public class DeviceGroup extends CustomBaseEntity {

	@Serial
	private static final long serialVersionUID = 1L;

	/**
	 * 分组名称
	 */
	@Schema(description = "分组名称")
	@ColumnType(value = ColumnTypeEnum.VARCHAR, length = 128)
	private String name;

	/**
	 * 分组描述
	 */
	@Schema(description = "分组描述")
	@ColumnType(value = ColumnTypeEnum.VARCHAR, length = 512)
	private String describeInfo;

	/**
	 * 父分组ID
	 */
	@Schema(description = "父分组ID")
	private Long parentId;

	/**
	 * 创建者ID
	 */
	@Schema(description = "创建者ID")
	private Long creatorId;
}
