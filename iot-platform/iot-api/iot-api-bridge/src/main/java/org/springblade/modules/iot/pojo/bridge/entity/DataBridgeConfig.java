

package org.springblade.modules.iot.pojo.bridge.entity;
import org.springblade.modules.iot.common.enums.BridgeType;

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
import org.springblade.modules.iot.common.enums.SourceScope;

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



  /** 配置名称 */
  @TableField(value = "name")
  @AutoColumn(comment = "配置名称", length = 128, defaultValueType = DefaultValueEnum.NULL)
  private String name;

  /** 源范围：ALL_PRODUCTS-所有产品，SPECIFIC_PRODUCTS-指定产品，APPLICATION-应用 */
  @TableField(value = "source_scope")
  @AutoColumn(comment = "源范围：ALL_PRODUCTS-所有产品，SPECIFIC_PRODUCTS-指定产品，APPLICATION-应用", defaultValueType = DefaultValueEnum.NULL)

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


  /** 描述 */
  @TableField(value = "description")
  @ColumnType("text")
  @AutoColumn(comment = "描述", defaultValueType = DefaultValueEnum.NULL)
  private String description;





  /** 源范围枚举 */

  /** 桥接类型枚举 */
}
