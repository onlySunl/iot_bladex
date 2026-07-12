package org.springblade.modules.iot.pojo.entity;

import com.baomidou.mybatisplus.annotation.TableName;
import com.tangzc.autotable.annotation.Index;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;
import lombok.EqualsAndHashCode;
import org.springblade.modules.iot.common.entity.CustomBaseEntity;

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

	private static final long serialVersionUID = 1L;

	@Schema(description = "产品Key")
	@Index
	private String productKey;

	@Schema(description = "设备ID")
	@Index
	private String deviceId;

	@Schema(description = "消息ID")
	private String msgId;

	@Schema(description = "日志类型: PROPERTY, EVENT, FUNCTION, OTA")
	private String type;

	@Schema(description = "操作")
	private String action;

	@Schema(description = "操作类型: READ, WRITE, REPORT")
	private String option;

	@Schema(description = "数据内容（JSON）")
	private String data;

}
