

package org.springblade.modules.iot.pojo.entity;

import io.swagger.v3.oas.annotations.media.Schema;
import java.util.Date;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import com.baomidou.mybatisplus.annotation.TableName;
import com.baomidou.mybatisplus.annotation.TableField;
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

  /** 主键ID */

  /** rulego规则链ID */
  @Schema(description = "rulego规则链ID")
  @TableField("rulego_id")
  private String rulegoId;

  /** 规则链名称 */
  @Schema(description = "规则链名称")
  @TableField("chain_name")
  private String chainName;

  /** 执行ID */
  @Schema(description = "执行ID")
  @TableField("execution_id")
  private String executionId;

  /** 输入数据 */
  @Schema(description = "输入数据")
  @TableField("input_data")
  private String inputData;

  /** 输出数据 */
  @Schema(description = "输出数据")
  @TableField("output_data")
  private String outputData;

  /** 执行状态：success-成功，failed-失败 */
  @Schema(description = "执行状态")
  @TableField("execution_status")
  private String executionStatus;

  /** 错误信息 */
  @Schema(description = "错误信息")
  @TableField("error_message")
  private String errorMessage;

  /** 执行耗时(毫秒) */
  @Schema(description = "执行耗时")
  @TableField("execution_time")
  private Long executionTime;

}
