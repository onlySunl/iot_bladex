/*
 *
 *
 *
 *
 */

package org.springblade.modules.iot.pojo.entity;

import java.time.LocalDateTime;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import com.baomidou.mybatisplus.annotation.TableName;
import com.baomidou.mybatisplus.annotation.TableField;
import org.springblade.common.entity.CustomBaseEntity;
@Builder
@NoArgsConstructor
@AllArgsConstructor
@TableName("iot_data_bridge_config")
@Data
public class DataBridgeConfig extends CustomBaseEntity {

  /** 配置名称 */
  @TableField("name")
  private String name;

  /** 源范围：ALL_PRODUCTS-所有产品，SPECIFIC_PRODUCTS-指定产品，APPLICATION-应用 */
  @TableField("source_scope")
  @Enumerated(EnumType.STRING)
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
  @Enumerated(EnumType.STRING)
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

  /** 描述 */
  @TableField("description")
  private String description;

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
