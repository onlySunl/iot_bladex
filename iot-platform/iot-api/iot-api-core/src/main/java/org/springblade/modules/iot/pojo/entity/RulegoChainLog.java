

package org.springblade.modules.iot.pojo.entity;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableName;
import com.tangzc.autotable.annotation.AutoColumn;
import com.tangzc.autotable.annotation.ColumnType;
import com.tangzc.autotable.annotation.enums.DefaultValueEnum;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springblade.common.entity.CustomBaseEntity;

/**
 * rulego规则链执行日志实体
 *
 * @author gitee.com/NexIoT
 * @since 2025/01/15
 */
@TableName("rulego_chain_log")
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class RulegoChainLog extends CustomBaseEntity {

  private static final long serialVersionUID = 1L;



  /** rulego规则链ID */
  @Schema(description = "rulego规则链ID")
  @TableField(value = "rulego_id")
  @AutoColumn(comment = "rulego规则链ID", length = 128, defaultValueType = DefaultValueEnum.NULL)
  private String rulegoId;

  /** 规则链名称 */
  @Schema(description = "规则链名称")
  @TableField(value = "chain_name")
  @AutoColumn(comment = "规则链名称", length = 128, defaultValueType = DefaultValueEnum.NULL)
  private String chainName;

  /** 执行ID */
  @Schema(description = "执行ID")
  @TableField(value = "execution_id")
  @AutoColumn(comment = "执行ID", length = 128, defaultValueType = DefaultValueEnum.NULL)
  private String executionId;

  /** 输入数据 */
  @Schema(description = "输入数据")
  @TableField(value = "input_data")
  @ColumnType("text")
  @AutoColumn(comment = "输入数据", defaultValueType = DefaultValueEnum.NULL)
  private String inputData;

  /** 输出数据 */
  @Schema(description = "输出数据")
  @TableField(value = "output_data")
  @ColumnType("text")
  @AutoColumn(comment = "输出数据", defaultValueType = DefaultValueEnum.NULL)
  private String outputData;

  /** 执行状态：success-成功，failed-失败 */
  @Schema(description = "执行状态")
  @TableField(value = "execution_status")
  @AutoColumn(comment = "执行状态：success-成功，failed-失败", length = 32, defaultValueType = DefaultValueEnum.NULL)
  private String executionStatus;

  /** 错误信息 */
  @Schema(description = "错误信息")
  @TableField(value = "error_message")
  @ColumnType("text")
  @AutoColumn(comment = "错误信息", defaultValueType = DefaultValueEnum.NULL)
  private String errorMessage;

  /** 执行耗时(毫秒) */
  @Schema(description = "执行耗时")
  @TableField(value = "execution_time")
  @AutoColumn(comment = "执行耗时(毫秒)", defaultValueType = DefaultValueEnum.NULL)
  private Long executionTime;

}
