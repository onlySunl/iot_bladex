

package org.springblade.modules.iot.databridge.entity;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableName;
import com.tangzc.autotable.annotation.AutoColumn;
import com.tangzc.autotable.annotation.ColumnType;
import com.tangzc.autotable.annotation.enums.DefaultValueEnum;
import org.springblade.common.entity.CustomBaseEntity;
import java.time.LocalDateTime;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

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

  private static final long serialVersionUID = 1L;

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;

  /** 配置名称 */
  @TableField(value = "name")
  @AutoColumn(comment = "配置名称", length = 128, defaultValueType = DefaultValueEnum.NULL)
  private String name;

  /** 源范围：ALL_PRODUCTS-所有产品，SPECIFIC_PRODUCTS-指定产品，APPLICATION-应用 */
  @TableField(value = "source_scope")
  @AutoColumn(comment = "源范围：ALL_PRODUCTS-所有产品，SPECIFIC_PRODUCTS-指定产品，APPLICATION-应用", defaultValueType = DefaultValueEnum.NULL)
  @Enumerated(EnumType.STRING)
  private SourceScope sourceScope;

  /** 源产品KEY列表JSON（当source_scope=SPECIFIC_PRODUCTS时使用） */
  @TableField(value = "source_product_keys")
  @AutoColumn(comment = "源产品KEY列表JSON（当source_scope=SPECIFIC_PRODUCTS时使用）", length = 128, defaultValueType = DefaultValueEnum.NULL)
  private String sourceProductKeys;

  /** 源应用ID（当source_scope=APPLICATION时使用） */
  @TableField(value = "source_application_id")
  @AutoColumn(comment = "源应用ID（当source_scope=APPLICATION时使用）", defaultValueType = DefaultValueEnum.NULL)
  private Long sourceApplicationId;

  /** 目标资源ID */
  @TableField(value = "target_resource_id")
  @AutoColumn(comment = "目标资源ID", defaultValueType = DefaultValueEnum.NULL)
  private Long targetResourceId;

  /** 桥接类型(JDBC,KAFKA,MQTT,HTTP,IOTDB,INFLUXDB等) */
  @TableField(value = "bridge_type")
  @AutoColumn(comment = "桥接类型(JDBC,KAFKA,MQTT,HTTP,IOTDB,INFLUXDB等)", defaultValueType = DefaultValueEnum.NULL)
  @Enumerated(EnumType.STRING)
  private BridgeType bridgeType;

  /** 模板内容（SQL、JSON等） */
  @TableField(value = "template")
  @ColumnType("text")
  @AutoColumn(comment = "模板内容（SQL、JSON等）", defaultValueType = DefaultValueEnum.NULL)
  private String template;

  /** Magic脚本内容（用户自定义处理逻辑） */
  @TableField(value = "magic_script")
  @ColumnType("text")
  @AutoColumn(comment = "Magic脚本内容（用户自定义处理逻辑）", defaultValueType = DefaultValueEnum.NULL)
  private String magicScript;

  /** 统一配置JSON */
  @TableField(value = "config")
  @ColumnType("text")
  @AutoColumn(comment = "统一配置JSON", defaultValueType = DefaultValueEnum.NULL)
  private String config;

  /** 状态：0禁用，1启用 */
  @TableField(value = "status")
  @AutoColumn(comment = "状态：0禁用，1启用", defaultValueType = DefaultValueEnum.NULL)
  private Integer status;

  /** 描述 */
  @TableField(value = "description")
  @ColumnType("text")
  @AutoColumn(comment = "描述", defaultValueType = DefaultValueEnum.NULL)
  private String description;

  /** 创建者 */
  @TableField(value = "create_by")
  @AutoColumn(comment = "创建者", length = 255, defaultValueType = DefaultValueEnum.NULL)
  private String createBy;

  /** 创建时间 */
  @TableField(value = "create_time")
  @AutoColumn(comment = "创建时间", defaultValueType = DefaultValueEnum.NULL)
  private LocalDateTime createTime;

  /** 更新者 */
  @TableField(value = "update_by")
  @AutoColumn(comment = "更新者", length = 255, defaultValueType = DefaultValueEnum.NULL)
  private String updateBy;

  /** 更新时间 */
  @TableField(value = "update_time")
  @AutoColumn(comment = "更新时间", defaultValueType = DefaultValueEnum.NULL)
  private LocalDateTime updateTime;

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
