package org.springblade.modules.iot.pojo.entity;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableName;
import com.tangzc.autotable.annotation.AutoColumn;
import com.tangzc.autotable.annotation.ColumnType;
import com.tangzc.autotable.annotation.Index;
import com.tangzc.autotable.annotation.enums.DefaultValueEnum;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;
import lombok.EqualsAndHashCode;
import org.springblade.common.entity.CustomBaseEntity;

import java.io.Serial;

/**
 * 设备日志 实体
 *
 * @author pmc
 */
@Data
@TableName("iot_device_log")
@EqualsAndHashCode(callSuper = true)
@Schema(description = "DeviceLog对象")
public class DeviceLog extends CustomBaseEntity {

	@Serial
	private static final long serialVersionUID = 1L;

	/**
	 * 产品Key
	 */
	@TableField(value = "product_key")
	@AutoColumn(comment = "产品Key", length = 64, defaultValueType = DefaultValueEnum.NULL)
	@Index
	private String productKey;

	/**
	 * 设备ID
	 */
	@TableField(value = "device_id")
	@AutoColumn(comment = "设备ID", length = 64, defaultValueType = DefaultValueEnum.NULL)
	@Index
	private String deviceId;

	/**
	 * 消息ID
	 */
	@TableField(value = "msg_id")
	@AutoColumn(comment = "消息ID", length = 64, defaultValueType = DefaultValueEnum.NULL)
	private String msgId;

	/**
	 * 日志类型: PROPERTY, EVENT, FUNCTION, OTA
	 */
	@TableField(value = "type")
	@AutoColumn(comment = "日志类型", length = 32, defaultValueType = DefaultValueEnum.NULL)
	private String type;

	/**
	 * 操作
	 */
	@TableField(value = "action")
	@AutoColumn(comment = "操作", length = 64, defaultValueType = DefaultValueEnum.NULL)
	private String action;

	/**
	 * 操作类型: READ, WRITE, REPORT
	 */
	@TableField(value = "option")
	@AutoColumn(comment = "操作类型", length = 32, defaultValueType = DefaultValueEnum.NULL)
	private String option;

	/**
	 * 数据内容（JSON）
	 */
	@TableField(value = "data")
	@AutoColumn(comment = "数据内容", defaultValueType = DefaultValueEnum.NULL)
	@ColumnType("text")
	private String data;

}
