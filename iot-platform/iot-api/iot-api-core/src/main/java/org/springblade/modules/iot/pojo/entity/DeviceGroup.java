package org.springblade.modules.iot.pojo.entity;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableName;
import com.tangzc.autotable.annotation.AutoColumn;
import com.tangzc.autotable.annotation.ColumnType;
import com.tangzc.autotable.annotation.enums.DefaultValueEnum;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;
import lombok.EqualsAndHashCode;
import org.springblade.common.entity.CustomBaseEntity;

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

	@TableField(value = "name")
	@AutoColumn(comment = "分组名称",length = 128, defaultValueType = DefaultValueEnum.EMPTY_STRING)
	private String name;

	/**
	 * 分组描述
	 */
	@TableField(value = "describe_info")
	@AutoColumn(comment = "分组名称",length = 512, defaultValueType = DefaultValueEnum.EMPTY_STRING)
	private String describeInfo;

	/**
	 * 父分组ID
	 */
	@TableField(value = "parent_id")
	@AutoColumn(comment = "父分组ID",length = 512, defaultValueType = DefaultValueEnum.NULL)
	private Long parentId;

}
