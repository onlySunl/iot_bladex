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
 * 协议定义实体
 *
 * @author blade-iot
 */
@Data
@TableName("iot_protocol")
@EqualsAndHashCode(callSuper = true)
@Schema(description = "协议定义")
public class Protocol extends CustomBaseEntity {

	@Serial
	private static final long serialVersionUID = 1L;

	/**
	 * 协议编码
	 */
	@TableField(value = "code")
	@AutoColumn(comment = "协议编码", length = 50, defaultValueType = DefaultValueEnum.NULL)
	private String code;

	/**
	 * 协议名称
	 */
	@TableField(value = "name")
	@AutoColumn(comment = "协议名称", length = 100, defaultValueType = DefaultValueEnum.NULL)
	private String name;

	/**
	 * 协议类型: MQTT, HTTP, TCP, UDP, COAP, MODBUS, OPC_UA
	 */
	@TableField(value = "type")
	@AutoColumn(comment = "协议类型", length = 20, defaultValueType = DefaultValueEnum.NULL)
	private String type;

	/**
	 * 协议描述
	 */
	@TableField(value = "description")
	@AutoColumn(comment = "协议描述", defaultValueType = DefaultValueEnum.NULL)
	@ColumnType("text")
	private String description;

	/**
	 * 编解码器类名
	 */
	@TableField(value = "codec_class")
	@AutoColumn(comment = "编解码器类名", length = 255, defaultValueType = DefaultValueEnum.NULL)
	private String codecClass;

	/**
	 * 协议配置(JSON)
	 */
	@TableField(value = "config")
	@AutoColumn(comment = "协议配置", defaultValueType = DefaultValueEnum.NULL)
	@ColumnType("text")
	private String config;

	/**
	 * 状态: 0-禁用 1-启用
	 */
	@TableField(value = "status")
	@AutoColumn(comment = "状态", defaultValueType = DefaultValueEnum.NULL)
	private Integer status;
}
