/*
 *
 * Copyright (c) 2025, IoT-Universal. All Rights Reserved.
 *
 * @Description: 本文件由 Aleo 开发并拥有版权，未经授权严禁擅自商用、复制或传播。
 * @Author: Aleo
 * @Email: wo8335224@gmail.com
 * @Wechat: outlookFil
 *
 *
 */

package org.springblade.modules.iot.pojo.entity;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableName;
import com.tangzc.autotable.annotation.AutoColumn;
import com.tangzc.autotable.annotation.ColumnType;
import com.tangzc.autotable.annotation.enums.DefaultValueEnum;
import org.springblade.common.entity.CustomBaseEntity;
import java.util.Date;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * 网关轮询指令实体
 * 
 * @author Aleo
 * @date 2025-10-26
 */
@Data
@TableName("iot_gateway_polling_command")
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class GatewayPollingCommand extends CustomBaseEntity {

  private static final long serialVersionUID = 1L;

  /** 主键ID */

  /** 网关产品KEY */
  @TableField(value = "gateway_product_key")
  @AutoColumn(comment = "网关产品KEY", length = 128, defaultValueType = DefaultValueEnum.NULL)
  private String gatewayProductKey;

  /** 网关设备ID */
  @TableField(value = "gateway_device_id")
  @AutoColumn(comment = "网关设备ID", length = 128, defaultValueType = DefaultValueEnum.NULL)
  private String gatewayDeviceId;

  /** 从站设备ID (可选) */
  @TableField(value = "slave_device_id")
  @AutoColumn(comment = "从站设备ID (可选)", length = 128, defaultValueType = DefaultValueEnum.NULL)
  private String slaveDeviceId;

  /** 指令名称 */
  @TableField(value = "command_name")
  @AutoColumn(comment = "指令名称", length = 128, defaultValueType = DefaultValueEnum.NULL)
  private String commandName;

  /** 执行顺序 */
  @TableField(value = "execution_order")
  @AutoColumn(comment = "执行顺序", defaultValueType = DefaultValueEnum.NULL)
  private Integer executionOrder;

  /** 完整的轮询指令(HEX格式) */
  @TableField(value = "command_hex")
  @AutoColumn(comment = "完整的轮询指令(HEX格式)", length = 255, defaultValueType = DefaultValueEnum.NULL)
  private String commandHex;

  /** 指令类型: MODBUS/S7/OPCUA/CUSTOM */
  @TableField(value = "command_type")
  @AutoColumn(comment = "指令类型: MODBUS", length = 32, defaultValueType = DefaultValueEnum.NULL)
  private String commandType;

  /** 协议参数JSON (用于前端回显编辑) */
  @TableField(value = "protocol_params")
  @AutoColumn(comment = "协议参数JSON (用于前端回显编辑)", length = 255, defaultValueType = DefaultValueEnum.NULL)
  private String protocolParams;

  /** 属性映射JSON (寄存器->物模型属性) */
  @TableField(value = "property_mapping")
  @AutoColumn(comment = "属性映射JSON (寄存器->物模型属性)", length = 255, defaultValueType = DefaultValueEnum.NULL)
  private String propertyMapping;

  /** 数据解析脚本 (可选) */
  @TableField(value = "data_parser_script")
  @ColumnType("text")
  @AutoColumn(comment = "数据解析脚本 (可选)", defaultValueType = DefaultValueEnum.NULL)
  private String dataParserScript;

  /** 是否启用 */
  @TableField(value = "enabled")
  @AutoColumn(comment = "是否启用", defaultValueType = DefaultValueEnum.NULL)
  private Boolean enabled;

  /** 超时时间(ms) */
  @TableField(value = "timeout_ms")
  @AutoColumn(comment = "超时时间(ms)", defaultValueType = DefaultValueEnum.NULL)
  private Integer timeoutMs;

  /** 描述 */
  @TableField(value = "description")
  @ColumnType("text")
  @AutoColumn(comment = "描述", defaultValueType = DefaultValueEnum.NULL)
  private String description;

  /** 创建时间 */
  @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")

  /** 更新时间 */
  @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
}
