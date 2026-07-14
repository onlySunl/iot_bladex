/*
 *
 * Copyright (c) 2025, NexIoT. All Rights Reserved.
 *
 * @Description: 本文件由 gitee.com/NexIoT 开发并拥有版权，未经授权严禁擅自商用、复制或传播。
 * @Author: gitee.com/NexIoT
 * @Email: wo8335224@gmail.com
 * @Wechat: outlookFil
 *
 *
 */

package org.springblade.modules.iot.pojo.bridge.entity;

import java.time.LocalDateTime;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import com.baomidou.mybatisplus.annotation.TableName;
import com.baomidou.mybatisplus.annotation.TableField;
import org.springblade.common.entity.CustomBaseEntity;
/**
 * 数据桥接配置实体
 *
 * @author gitee.com/NexIoT
 * @version 1.0
 * @since 2025/1/15
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@TableName("iot_data_bridge_config")
public class DataBridgeConfig extends CustomBaseEntity {

  /** 配置名称 */
  @TableField("name")
  private String name;

  /** 源范围：ALL_PRODUCTS-所有产品，SPECIFIC_PRODUCTS-指定产品，APPLICATION-应用 */
  @TableField("source_scope")
  private SourceScope sourceScope;

  /** 源产品KEY列表JSON（当source_scope=SPECIFIC_PRODUCTS时使用） */
  @TableField("source_product_keys")
  private String sourceProductKeys;

  /** 源应用ID（当source_scope=APPLICATION时使用） */
  @TableField("source_application_id")
  private Long sourceApplicationId;

  /** 目标资源ID */
  @TableField("target_resource_id")
  private Long targetResourceId;

  /** 桥接类型(JDBC,KAFKA,MQTT,HTTP,IOTDB,INFLUXDB等) */
  @TableField("bridge_type")
  private BridgeType bridgeType;

  /** 模板内容（SQL、JSON等） */
  @TableField("template")
  private String template;

  /** Magic脚本内容（用户自定义处理逻辑） */
  @TableField("magic_script")
  private String magicScript;

  /** 统一配置JSON */
  @TableField("config")
  private String config;

  /** 状态：0禁用，1启用 */

  /** 描述 */
  @TableField("description")
  private String description;

  /** 创建者 */

  /** 创建时间 */

  /** 更新者 */

  /** 更新时间 */

  /** 源范围枚举 */
  public enum SourceScope {
    ALL_PRODUCTS, // 所有产品
    SPECIFIC_PRODUCTS, // 指定产品
    APPLICATION // 应用级别
  }

  /** 桥接类型枚举 */
  public enum BridgeType {
    JDBC,
    KAFKA,
    IOTDB,
    INFLUXDB,
    MQTT,
    HTTP,
    REDIS,
    ELASTICSEARCH
  }
}
