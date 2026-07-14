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
import java.util.Date;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import com.baomidou.mybatisplus.annotation.TableName;
import com.baomidou.mybatisplus.annotation.TableField;
import org.springblade.common.entity.CustomBaseEntity;
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

  /** 主键ID */

  /** 网关产品KEY */
  @TableField("gateway_product_key")
  private String gatewayProductKey;

  /** 网关设备ID */
  @TableField("gateway_device_id")
  private String gatewayDeviceId;

  /** 从站设备ID (可选) */
  @TableField("slave_device_id")
  private String slaveDeviceId;

  /** 指令名称 */
  @TableField("command_name")
  private String commandName;

  /** 执行顺序 */
  @TableField("execution_order")
  private Integer executionOrder;

  /** 完整的轮询指令(HEX格式) */
  @TableField("command_hex")
  private String commandHex;

  /** 指令类型: MODBUS/S7/OPCUA/CUSTOM */
  @TableField("command_type")
  private String commandType;

  /** 协议参数JSON (用于前端回显编辑) */
  @TableField("protocol_params")
  private String protocolParams;

  /** 属性映射JSON (寄存器->物模型属性) */
  @TableField("property_mapping")
  private String propertyMapping;

  /** 数据解析脚本 (可选) */
  @TableField("data_parser_script")
  private String dataParserScript;

  /** 是否启用 */
  @TableField("enabled")
  private Boolean enabled;

  /** 超时时间(ms) */
  @TableField("timeout_ms")
  private Integer timeoutMs;

  /** 描述 */
  @TableField("description")
  private String description;

  /** 创建时间 */
  @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")

  /** 更新时间 */
  @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
}
